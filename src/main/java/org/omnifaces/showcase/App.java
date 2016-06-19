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

import static org.omnifaces.util.Beans.fireEvent;
import static org.omnifaces.util.Faces.getContext;
import static org.omnifaces.util.Faces.getImplInfo;
import static org.omnifaces.util.Faces.getRequest;
import static org.omnifaces.util.Faces.getRequestHeader;
import static org.omnifaces.util.Faces.getResourcePaths;
import static org.omnifaces.util.Faces.getServerInfo;
import static org.omnifaces.util.Faces.getViewId;
import static org.omnifaces.util.Faces.isPostback;
import static org.omnifaces.util.ResourcePaths.stripPrefixPath;
import static org.omnifaces.util.Utils.isEmpty;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.inject.Named;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.omnifaces.model.tree.TreeModel;
import org.omnifaces.util.Faces;
import org.primefaces.config.PrimeConfiguration;

@Named
@ApplicationScoped
public class App {

	// Constants ------------------------------------------------------------------------------------------------------

	private static final String SHOWCASE_PATH = "/showcase/";

	// Properties -----------------------------------------------------------------------------------------------------

	private String index;
	private Page menu;
	private Map<String, Page> pages;
	private String omniFacesVersion;
	private boolean snapshot;
	private String facesVersion;
	private String primeFacesVersion;
	private String serverVersion;

	// Initialization -------------------------------------------------------------------------------------------------

	@PostConstruct
	public void init() {
		index = loadIndex();
		menu = new Page(null);
		pages = new HashMap<>();
		fillMenuAndPages(menu, pages);
		omniFacesVersion = Faces.class.getPackage().getSpecificationVersion().replaceAll("-\\d+$", "");
		snapshot = omniFacesVersion.contains("-");
		facesVersion = getImplInfo();
		primeFacesVersion = new PrimeConfiguration(getContext()).getBuildVersion();
		serverVersion = getServerInfo();
	}

	private static String loadIndex() {
		try {
			return scrape("http://omnifaces.org", "#main_content").outerHtml();
		}
		catch (Exception justIgnoreItTheGetterWillTryItAgain) {
			return null;
		}
	}

	private static void fillMenuAndPages(Page menu, Map<String, Page> pages) {
		Set<String> resourcePaths = getResourcePaths(SHOWCASE_PATH);
		Set<String> groupPaths = new TreeSet<>(resourcePaths);

		for (String groupPath : groupPaths) {
			String groupName = groupPath.split("/")[2];
			TreeModel<Page> group = menu.addChildNode(new Page(groupName));
			Set<String> pagePaths = new TreeSet<>(getResourcePaths(groupPath));

			for (String pagePath : pagePaths) {
				String viewId = stripPrefixPath(SHOWCASE_PATH, pagePath);
				String extensionlessViewId = viewId.split("\\.")[0];
				String title = extensionlessViewId.split("/")[2];
				Page page = new Page(pagePath, extensionlessViewId, title);
				group.addChildNode(page);
				pages.put(viewId, page);
			}
		}
	}

	// Bot ------------------------------------------------------------------------------------------------------------

	static Elements scrape(String url, String selector) throws IOException {
		return Jsoup.connect(url).userAgent("OmniBot 0.1 (+http://showcase.omnifaces.org)").get().select(selector);
	}

	// Producers ------------------------------------------------------------------------------------------------------

	@Produces
	@Model
	public Page getPage() {
		if (!(isPostback() || isEmpty(getRequestHeader("referer")))) { // Skip postbacks and (generally) bots.
			fireEvent(new PageView(getRequest()));
		}

		return pages.getOrDefault(getViewId(), menu).loadIfNecessary();
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

	public String getOmniFacesVersion() {
		return omniFacesVersion;
	}

	public boolean isSnapshot() {
		return snapshot;
	}

	public String getFacesVersion() {
		return facesVersion;
	}

	public String getPrimeFacesVersion() {
		return primeFacesVersion;
	}

	public String getServerVersion() {
		return serverVersion;
	}

}