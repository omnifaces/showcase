/*
 * Copyright 2012 OmniFaces.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package org.omnifaces.showcase;

import static java.lang.Boolean.parseBoolean;
import static java.lang.String.format;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toSet;
import static org.omnifaces.showcase.App.scrape;
import static org.omnifaces.util.Faces.evaluateExpressionGet;
import static org.omnifaces.util.Faces.getMetadataAttributes;
import static org.omnifaces.util.Faces.getResourceAsStream;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

import jakarta.faces.FacesException;

import org.jsoup.select.Elements;
import org.omnifaces.model.tree.ListTreeModel;

/**
 * This class represents a page. All pages are available as a tree model by {@link App#getMenu()}. The current page is
 * produced as model (request scoped named bean) by {@link App#getPage()} based on the current view ID.
 *
 * @author Bauke Scholtz
 */
public class Page extends ListTreeModel<Page> {

	// Constants ------------------------------------------------------------------------------------------------------

	private static final long serialVersionUID = 1L;
	private static final String MODULE_NAME = "org.omnifaces";
	private static final String API_PATH = MODULE_NAME + "/org/omnifaces/";
	private static final String ERROR_LOADING_PAGE_DESCRIPTION = "Unable to load description of %s";
	private static final String ERROR_LOADING_PAGE_SOURCE = "Unable to load source code of %s";

	// Properties -----------------------------------------------------------------------------------------------------

	private String title;
	private String path;
	private String viewId;
	private String description;
	private String since;
	private boolean deprecated;
	private List<Source> sources;
	private Documentation documentation;
	private AtomicBoolean loaded = new AtomicBoolean();

	// Constructors ---------------------------------------------------------------------------------------------------

	public Page() {
		// Keep default c'tor alive for CDI.
	}

	Page(String title) {
		this.title = title;
	}

	Page(String path, String viewId, String title) {
		this.path = path;
		this.viewId = viewId;
		this.title = title;
		this.deprecated = parseBoolean((String) getMetadataAttributes(viewId).get("deprecated"));
	}

	// Initialization -------------------------------------------------------------------------------------------------

	Page loadIfNecessary() {
		if (!loaded.getAndSet(true) && path != null) {
			try {
				var attributes = getMetadataAttributes();
				var apiPaths = getCommaSeparatedAttribute(attributes, "api.path");
				var vdlPaths = getCommaSeparatedAttribute(attributes, "vdl.paths");
				var srcPaths = getCommaSeparatedAttribute(attributes, "src.paths");
				var jsPaths = getCommaSeparatedAttribute(attributes, "js.paths");
				var javadoc = loadJavadoc(apiPaths);

				if (javadoc != null) {
					description = findDescription(javadoc);
					fillApiPathsWithSeeAlso(javadoc, apiPaths);
					since = findSinceVersion(javadoc);
				}
				else {
					since = attributes.containsKey("since") ? attributes.get("since").toString() : "1.0";
				}

				sources = loadSources(path, srcPaths);
				documentation = apiPaths.size() + vdlPaths.size() + jsPaths.size() > 0 ? new Documentation(apiPaths, vdlPaths, jsPaths) : null;
			}
			catch (Exception e) {
				loaded.set(false);
				throw e;
			}
		}

		return this;
	}

	private static Set<String> getCommaSeparatedAttribute(Map<String, Object> attributes, String key) {
		var attribute = (String) attributes.get(key);
		return new LinkedHashSet<>(attribute == null ? emptyList() : asList(attribute.trim().split("\\s*,\\s*")));
	}

	private static Elements loadJavadoc(Set<String> apiPaths) {
		if (apiPaths.size() == 1) {
			var apiPath = API_PATH + apiPaths.iterator().next();
			apiPaths.clear();
			apiPaths.add(apiPath);
			var url = format("%s%s.html", evaluateExpressionGet("#{_apiURL}"), apiPath);

			try {
				// TODO: build javadoc.jar into webapp somehow and scrape from it instead.
				return scrape(url, ".description>ul>li");
			}
			catch (IOException e) {
				throw new FacesException(format(ERROR_LOADING_PAGE_DESCRIPTION, url), e);
			}
		}

		return null;
	}

	private static String findDescription(Elements javadoc) {
		var descriptionBlock = javadoc.select(".block");

		for (var link : javadoc.select("a")) { // Turn relative links into absolute links.
			link.attr("href", link.absUrl("href"));
		}

		for (var pre : descriptionBlock.select("pre")) { // Enable prettify on code blocks.
			var content = pre.addClass("prettyprint").html().trim().replace("\n ", "\n");
			var type = content.startsWith("&lt;") ? "xhtml" : "java";
			pre.html("<code class='lang-" + type + "'>" + content + "</code>");
		}

		return descriptionBlock.outerHtml();
	}

	private static void fillApiPathsWithSeeAlso(Elements javadoc, Set<String> apiPaths) {
		var seeAlso = javadoc.select("dt:has(.seeLabel)+dd a:has(code)");

		for (var link : seeAlso) {
			var href = link.absUrl("href");
			apiPaths.add(href.substring(href.indexOf(API_PATH), href.lastIndexOf('.')));
		}
	}

	private static String findSinceVersion(Elements javadoc) {
	    var since = javadoc.select("dt:contains(Since)+dd").first();
		return since != null ? since.text() : "1.0";
	}

	private static List<Source> loadSources(String pagePath, Set<String> srcPaths) {
	    var sources = new ArrayList<Source>(1 + srcPaths.size());
		var sourceCode = loadSourceCode(pagePath);
		var meta = sourceCode.split("<ui:define name=\"demo-meta\">");
		var demo = sourceCode.split("<ui:define name=\"demo\">");
		var demoSourceCode = new StringBuilder();

		if (meta.length > 1) {
			// Yes, ugly, but it's faster than a XML parser and it's internal code anyway.
			demoSourceCode.append(meta[1].split("</ui:define>")[0].trim()).append("\n\n");
		}

		if (demo.length > 1) {
			demoSourceCode.append(demo[1].split("</ui:define>")[0].trim());
		}

		if (demoSourceCode.length() > 0) {
			// The 8 leading spaces are trimmed so that the whole demo code block is indented back.
			var code = demoSourceCode.toString().replace("\n        ", "\n").trim();
			sources.add(new Source("Demo", "xhtml", code));
		}

		for (var srcPath : srcPaths) {
			var title = srcPath;

			if (title.contains("/")) {
				title = title.substring(title.lastIndexOf('/') + 1);
			}

			if (title.endsWith(".java")) {
				title = title.substring(0, title.indexOf('.'));
			}

			var type = srcPath.substring(srcPath.lastIndexOf('.') + 1);
			var code = loadSourceCode((srcPath.startsWith("/") ? "" : "/WEB-INF/") + srcPath);
			sources.add(new Source(title, type, code));
		}

		return sources;
	}

	private static String loadSourceCode(String path) {
		var input = getResourceAsStream(path);

		if (input == null) {
			return "Source code is not available at " + path;
		}

		try {
			return new String(input.readAllBytes(), "UTF-8").replace("\t", "    "); // Tabs are in HTML <pre> presented as 8 spaces, which is too much.
		}
		catch (IOException e) {
			throw new FacesException(format(ERROR_LOADING_PAGE_SOURCE, path), e);
		}
	}

	// Getters/setters ------------------------------------------------------------------------------------------------

	public String getTitle() {
		return title;
	}

	public String getViewId() {
		return viewId;
	}

	public String getDescription() {
		return description;
	}

	public String getSince() {
		return since;
	}

	public boolean isDeprecated() {
		return deprecated;
	}

	public List<Source> getSources() {
		return sources;
	}

	public Documentation getDocumentation() {
		return documentation;
	}

	// Object overrides -----------------------------------------------------------------------------------------------

	@Override
	public boolean equals(Object other) {
		return other instanceof Page && title != null
			? title.equals(((Page) other).title)
			: other == this;
	}

	@Override
	public int hashCode() {
		return title != null
			? getClass().hashCode() + title.hashCode()
			: super.hashCode();
	}

	@Override
	public String toString() {
		return format("Page[title=%s,viewId=%s]", title, viewId);
	}

	// Nested classes -------------------------------------------------------------------------------------------------

	/**
	 * This class represents a source code snippet associated with the current page.
	 *
	 * @author Bauke Scholtz
	 */
	public static class Source implements Serializable {

		// Constants --------------------------------------------------------------------------------------------------

		private static final long serialVersionUID = 1L;

		// Properties -------------------------------------------------------------------------------------------------

		private String title;
		private String type;
		private String code;

		// Contructors ------------------------------------------------------------------------------------------------

		public Source(String title, String type, String code) {
			this.title = title;
			this.type = type;
			this.code = code;
		}

		// Getters/setters --------------------------------------------------------------------------------------------

		public String getTitle() {
			return title;
		}

		public String getType() {
			return type;
		}

		public String getCode() {
			return code;
		}

	}

	/**
	 * This class represents the API and VDL documentation paths associated with the current page.
	 *
	 * @author Bauke Scholtz
	 */
	public static class Documentation implements Serializable {

		// Constants --------------------------------------------------------------------------------------------------

		private static final long serialVersionUID = 1L;

		// Properties -------------------------------------------------------------------------------------------------

		private Set<String> api;
		private Set<String> src;
		private Set<String> vdl;
		private Set<String> js;

		// Contructors ------------------------------------------------------------------------------------------------

		public Documentation(Set<String> api, Set<String> vdl, Set<String> js) {
			this.api = api.isEmpty() ? api : new LinkedHashSet<>(asList(api.iterator().next()));
			src = api.stream().map(path -> path.replaceFirst(MODULE_NAME + "/", "")).collect(toSet());
			this.vdl = vdl;
			this.js = js;
		}

		// Getters/setters --------------------------------------------------------------------------------------------

		public Set<String> getApi() {
			return api;
		}

		public Set<String> getSrc() {
			return src;
		}

		public Set<String> getVdl() {
			return vdl;
		}

		public Set<String> getJs() {
			return js;
		}

	}

}