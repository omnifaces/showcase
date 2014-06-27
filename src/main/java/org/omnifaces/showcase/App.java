/*
 * Copyright 2012 OmniFaces.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package org.omnifaces.showcase;

import static org.omnifaces.util.ResourcePaths.stripPrefixPath;
import static org.omnifaces.util.Utils.isEmpty;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;

import javax.annotation.PostConstruct;
import javax.faces.FacesException;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.omnifaces.model.tree.TreeModel;
import org.omnifaces.showcase.Page.Documentation;
import org.omnifaces.showcase.Page.Source;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Utils;

@ManagedBean(eager=true)
@ApplicationScoped
public class App {

	// Constants ------------------------------------------------------------------------------------------------------

	private static final String SHOWCASE_PATH = "/showcase/";
	private static final String SHOWCASE_PROPERTIES_PATH = "/WEB-INF/showcase.properties";

	private static final String ERROR_MISSING_VERSION = "OmniFaces package is missing specification version.";
	private static final String ERROR_LOADING_SHOWCASE_PROPERTIES = "Unable to load " + SHOWCASE_PROPERTIES_PATH;
	private static final String ERROR_LOADING_PAGE_SOURCE = "Unable to load source code of %s";
	private static final String ERROR_MISSING_PROPERTY = "Property '%s' is missing in " + SHOWCASE_PROPERTIES_PATH;

	// Properties -----------------------------------------------------------------------------------------------------

	private String index;
	private Page menu;
	private Map<String, Page> pages;
	private String version;
	private boolean snapshot;
	private String poweredBy;

	// Initialization -------------------------------------------------------------------------------------------------

	@PostConstruct
	public void init() {
		index = initIndex();
		menu = new Page();
		fillMenu(menu);
		pages = new HashMap<>();
		fillPages(pages, menu);
		version = initVersion();
		snapshot = version.contains("SNAPSHOT");
		poweredBy = initPoweredBy();
	}

	private static String initIndex() {
		try {
			Elements mainContent = Jsoup.connect("http://omnifaces.org").get().select("#main_content");
			mainContent.select("h2").tagName("h3");
			return mainContent.outerHtml();
		}
		catch (Exception justIgnoreItTheGetterWillTryItAgain) {
			return null;
		}
	}

	private static void fillMenu(Page menu) {
		Properties properties = loadProperties();
		Set<String> resourcePaths = Faces.getResourcePaths(SHOWCASE_PATH);
		Set<String> groupPaths = new TreeSet<>(resourcePaths);

		for (String groupPath : groupPaths) {
			String groupName = groupPath.split("/")[2];
			TreeModel<Page> group = menu.addChildNode(new Page(groupName, null, null, null));
			Set<String> pagePaths = new TreeSet<>(Faces.getResourcePaths(groupPath));

			for (String pagePath : pagePaths) {
				group.addChildNode(createPage(groupName, properties, pagePath));
			}
		}
	}

	private static Properties loadProperties() {
		try {
			Properties properties = new Properties();
			properties.load(Faces.getResourceAsStream(SHOWCASE_PROPERTIES_PATH));
			return properties;
		}
		catch (IOException e) {
			throw new FacesException(ERROR_LOADING_SHOWCASE_PROPERTIES, e);
		}
	}

	private static Page createPage(String groupName, Properties properties, String viewId) {
		String title = viewId.split("/")[3].split("\\.")[0];
		List<Source> sources = new ArrayList<>();

		String sourceCode = loadSourceCode(viewId);
		String[] meta = sourceCode.split("<ui:define name=\"demo-meta\">");
		String[] demo = sourceCode.split("<ui:define name=\"demo\">");
		StringBuilder demoSourceCode = new StringBuilder();

		if (meta.length > 1) {
			// Yes, ugly, but it's faster than a XML parser and it's internal code anyway.
			demoSourceCode.append(meta[1].split("</ui:define>")[0].trim()).append("\n\n");
		}

		if (demo.length > 1) {
			demoSourceCode.append(demo[1].split("</ui:define>")[0].trim());
		}

		if (demoSourceCode.length() > 0) {
			// The 8 leading spaces are trimmed so that the whole demo code block is indented back.
			String source = demoSourceCode.toString().replace("\n        ", "\n").trim();
			sources.add(new Source("Demo", "xhtml", source));
		}

		String pageKey = groupName + "." + title;
		String sourcesKey = pageKey + ".sources";

		for (String sourceKey : getCommaSeparatedProperty(properties, sourcesKey)) {
			sources.add(createSource(properties, sourcesKey + "." + sourceKey));
		}

		return new Page(
			title, stripPrefixPath(SHOWCASE_PATH, viewId.split("\\.")[0]),
			sources, createDocumentation(properties, pageKey + ".documentation")
		);
	}

	private static String loadSourceCode(String path) {
		InputStream input = Faces.getResourceAsStream(path);

		if (input == null) {
			return "Source code is not available at " + path;
		}

		try {
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			Utils.stream(input, output);
			return new String(output.toByteArray(), "UTF-8")
				.replace("\t", "    "); // Tabs are in HTML <pre> presented as 8 spaces, which is too much.
		}
		catch (IOException e) {
			throw new FacesException(String.format(ERROR_LOADING_PAGE_SOURCE, path), e);
		}
	}

	private static Source createSource(Properties properties, String sourceKey) {
		String path = getMandatoryProperty(properties, sourceKey + ".path");

		String title = properties.getProperty(sourceKey + ".title");
		if (isEmpty(title)) {
			title = path;
			if (title.contains("/")) {
				title = title.substring(title.lastIndexOf('/') + 1, title.length());
			}
			if (title.contains(".")) {
				title = title.substring(0, title.indexOf('.'));
			}
		}

		String type = path.substring(path.lastIndexOf('.') + 1);
		String code = loadSourceCode(path);
		return new Source(title, type, code);
	}

	private static Documentation createDocumentation(Properties properties, String documentationKey) {
		String[] vdldocs = getCommaSeparatedProperty(properties, documentationKey + ".vdl.paths");
		String[] apidocs = getCommaSeparatedProperty(properties, documentationKey + ".api.paths");
		String[] codes = getCommaSeparatedProperty(properties, documentationKey + ".code.paths");
		return (vdldocs.length + apidocs.length + codes.length > 0)
			? new Documentation(vdldocs, apidocs, codes.length == 0 ? apidocs : codes)
			: null;
	}

	private static String[] getCommaSeparatedProperty(Properties properties, String key) {
		String property = properties.getProperty(key);

		if (property == null) {
			return new String[0];
		}

		return property.split("\\s*,\\s*");
	}

	private static String getMandatoryProperty(Properties properties, String key) {
		String property = properties.getProperty(key);

		if (property == null) {
			throw new IllegalArgumentException(String.format(ERROR_MISSING_PROPERTY, key));
		}

		return property;
	}

	private static void fillPages(Map<String, Page> pages, Page page) {
		for (TreeModel<Page> child : page) {
			Page childPage = (Page) child;
			String viewId = childPage.getViewId();

			if (viewId != null) {
				pages.put(viewId + ".xhtml", childPage);
				pages.put(SHOWCASE_PATH + viewId.substring(1) + ".xhtml", childPage);
			}

			fillPages(pages, childPage);
		}
	}

	private static String initVersion() {
		String version = Faces.class.getPackage().getSpecificationVersion();

		if (version == null) {
			throw new FacesException(ERROR_MISSING_VERSION);
		}

		return version.replaceAll("-\\d+$", "");
	}

	private static String initPoweredBy() {
		return String.format("%s%nOmniFaces %s%nPrimeFaces %s%n%s",
			Faces.getImplInfo(),
			Faces.class.getPackage().getSpecificationVersion(),
			"4.0", // Constants.VERSION is moved to RequestContext#getApplicatioContext(), however the access point is in turn request based instead of application based.
			Faces.getServerInfo());
	}

	// Getters --------------------------------------------------------------------------------------------------------

	public String getIndex() {
		if (index == null) {
			index = initIndex();
		}

		return index;
	}

	public TreeModel<Page> getMenu() {
		return menu;
	}

	public Map<String, Page> getPages() {
		return pages;
	}

	public String getVersion() {
		return version;
	}

	public boolean isSnapshot() {
		return snapshot;
	}

	public String getPoweredBy() {
		return poweredBy;
	}

}