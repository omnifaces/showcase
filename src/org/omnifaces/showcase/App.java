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

	private static final String ERROR_LOADING_SHOWCASE_PROPERTIES = "Unable to load " + SHOWCASE_PROPERTIES_PATH;
	private static final String ERROR_LOADING_PAGE_SOURCE = "Unable to load source code of %s";
	private static final String ERROR_MISSING_PROPERTY = "Property '%s' is missing in " + SHOWCASE_PROPERTIES_PATH;

	// Properties -----------------------------------------------------------------------------------------------------

	private Page menu;
	private Map<String, Page> pages;
	private String version;
	private String poweredBy;

	// Initialization -------------------------------------------------------------------------------------------------

	@PostConstruct
	public void init() {
		menu = new Page();
		fillMenu(menu);
		pages = new HashMap<String, Page>();
		fillPages(pages, menu);
		version = initVersion();
		poweredBy = initPoweredBy();
	}

	private static void fillMenu(Page menu) {
		Properties properties = loadProperties();
		Set<String> groupPaths = new TreeSet<String>(Faces.getResourcePaths(SHOWCASE_PATH));

		for (String groupPath : groupPaths) {
			TreeModel<Page> group = menu.addChildNode(new Page(groupPath.split("/")[2], null, null, null));
			Set<String> pagePaths = new TreeSet<String>(Faces.getResourcePaths(groupPath));

			for (String pagePath : pagePaths) {
				group.addChildNode(createPage(properties, pagePath));
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

	private static Page createPage(Properties properties, String viewId) {
		String title = viewId.split("/")[3].split("\\.")[0];
		List<Source> sources = new ArrayList<Source>();
		String[] demo = loadSourceCode(viewId).split("<ui:define name=\"demo\">");

		if (demo.length > 1) {
			sources.add(new Source("Demo", "xhtml", demo[1]
				.split("</ui:define>")[0] // Yes, ugly, but it's faster than a XML parser and it's internal code anyway.
				.replace("\n        ", "\n") // Trim 8 leading spaces so that the whole demo code block is indented back.
				.trim()));
		}

		String sourcesKey = title + ".sources";

		for (String sourceKey : getCommaSeparatedProperty(properties, sourcesKey)) {
			sources.add(createSource(properties, sourcesKey + "." + sourceKey));
		}

		return new Page(title, viewId, sources, createDocumentation(properties, title + ".documentation"));
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
		String title = getMandatoryProperty(properties, sourceKey + ".title");
		String path = getMandatoryProperty(properties, sourceKey + ".path");
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
				pages.put(viewId, childPage);
			}

			fillPages(pages, childPage);
		}
	}

	private static String initVersion() {
		String version = Faces.class.getPackage().getSpecificationVersion();
		return (version != null && !version.contains("SNAPSHOT")) ? version : null;
	}

	private static String initPoweredBy() {
		return String.format("%s%nOmniFaces %s%nPrimeFaces %s%n%s",
			Faces.getImplInfo(),
			Faces.class.getPackage().getSpecificationVersion(),
			org.primefaces.util.Constants.VERSION,
			Faces.getServerInfo());
	}

	// Getters --------------------------------------------------------------------------------------------------------

	public TreeModel<Page> getMenu() {
		return menu;
	}

	public Map<String, Page> getPages() {
		return pages;
	}

	public String getVersion() {
		return version;
	}

	public String getPoweredBy() {
		return poweredBy;
	}

}