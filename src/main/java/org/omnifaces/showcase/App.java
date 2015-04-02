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

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.annotation.PostConstruct;
import javax.faces.FacesException;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.omnifaces.model.tree.TreeModel;
import org.omnifaces.util.Faces;
import org.omnifaces.util.ResourcePaths;

@ManagedBean(eager=true)
@ApplicationScoped
public class App {

	// Constants ------------------------------------------------------------------------------------------------------

	private static final String SHOWCASE_PATH = "/showcase/";
	private static final String ERROR_MISSING_VERSION = "OmniFaces package is missing specification version.";

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
		index = loadIndex();
		menu = new Page();
		fillMenu(menu);
		pages = new HashMap<>();
		fillPages(pages, menu);
		version = initVersion();
		snapshot = version.contains("-SNAPSHOT") || version.contains("-RC");
		poweredBy = initPoweredBy();
	}

	private static String loadIndex() {
		try {
			return scrape("http://omnifaces.org", "#main_content").outerHtml();
		}
		catch (Exception justIgnoreItTheGetterWillTryItAgain) {
			return null;
		}
	}

	private static void fillMenu(Page menu) {
		Set<String> resourcePaths = Faces.getResourcePaths(SHOWCASE_PATH);
		Set<String> groupPaths = new TreeSet<>(resourcePaths);

		for (String groupPath : groupPaths) {
			String groupName = groupPath.split("/")[2];
			TreeModel<Page> group = menu.addChildNode(new Page(groupName));
			Set<String> pagePaths = new TreeSet<>(Faces.getResourcePaths(groupPath));

			for (String pagePath : pagePaths) {
				String viewId = ResourcePaths.stripPrefixPath(SHOWCASE_PATH, pagePath.split("\\.")[0]);
				String title = viewId.split("/")[2];
				group.addChildNode(new Page(pagePath, viewId, title));
			}
		}
	}

	private static void fillPages(Map<String, Page> pages, Page page) {
		for (TreeModel<Page> child : page) {
			Page childPage = (Page) child;
			String viewId = childPage.getViewId();

			if (viewId != null) {
				pages.put(viewId + ".xhtml", childPage);
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
			"5.1", // PrimeFaces Constants.VERSION is since 4.0 moved to RequestContext#getApplicationContext(), however the access point has changed from application based to request based.
			Faces.getServerInfo());
	}

	// Bot ------------------------------------------------------------------------------------------------------------

	static Elements scrape(String url, String selector) throws IOException {
		return Jsoup.connect(url).userAgent("OmniBot 0.1 (+http://showcase.omnifaces.org)").get().select(selector);
	}

	// Getters --------------------------------------------------------------------------------------------------------

	public String getIndex() {
		if (index == null) {
			index = loadIndex();
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