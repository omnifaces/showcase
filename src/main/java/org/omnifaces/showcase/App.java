/*
 * Copyright 2020 OmniFaces.
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

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;
import static org.omnifaces.util.Beans.fireEvent;
import static org.omnifaces.util.Faces.getContext;
import static org.omnifaces.util.Faces.getImplInfo;
import static org.omnifaces.util.Faces.getRequest;
import static org.omnifaces.util.Faces.getResourcePaths;
import static org.omnifaces.util.Faces.getServerInfo;
import static org.omnifaces.util.Faces.getViewId;
import static org.omnifaces.util.Faces.isPostback;
import static org.omnifaces.util.ResourcePaths.stripPrefixPath;
import static org.omnifaces.util.Servlets.getReferrer;
import static org.omnifaces.util.Utils.isEmpty;
import static org.omnifaces.util.Utils.unserializeURLSafe;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Model;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletRequest;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.omnifaces.config.OmniFaces;
import org.omnifaces.model.tree.TreeModel;
import org.omnifaces.resourcehandler.ResourceIdentifier;
import org.omnifaces.util.Faces;
import org.primefaces.config.PrimeEnvironment;

@Named
@ApplicationScoped
public class App {

	// Constants ------------------------------------------------------------------------------------------------------

	private static final String SHOWCASE_PATH = "/showcase/";

	// Properties -----------------------------------------------------------------------------------------------------

	private String index;
	private LocalDate indexLastLoaded;
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
		pages = new LinkedHashMap<>();
		fillMenuAndPages(menu, pages);
		omniFacesVersion = OmniFaces.getVersion();
		snapshot = OmniFaces.isSnapshot();
		facesVersion = getImplInfo();
		primeFacesVersion = new PrimeEnvironment(getContext()).getBuildVersion();
		serverVersion = getServerInfo();
	}

	private String loadIndex() {
		try {
			String index = scrape("https://omnifaces.org", "#main_content").outerHtml();
			indexLastLoaded = LocalDate.now();
			return index;
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
				String extensionlessViewId = viewId.split("\\.xhtml$")[0];
				String title = extensionlessViewId.split("/")[2];
				Page page = new Page(pagePath, extensionlessViewId, title);
				group.addChildNode(page);
				pages.put(viewId, page);
			}
		}
	}

	// Bot ------------------------------------------------------------------------------------------------------------

	static Elements scrape(String url, String selector) throws IOException {
		return Jsoup.connect(url).userAgent("OmniBot 0.2 (+https://showcase.omnifaces.org)").get().select(selector);
	}

	// Producers ------------------------------------------------------------------------------------------------------

	@Produces @Model
	public Page getPage() {
		HttpServletRequest request = getRequest();

		if (allowTrackingForStatistics(request)) {
			fireEvent(new PageView(request));
		}

		return pages.getOrDefault(getViewId(), menu).loadIfNecessary();
	}

	private static boolean allowTrackingForStatistics(HttpServletRequest request) {
		if (isPostback()) {
			return false;
		}

		String referrer = getReferrer(request);

		if (isEmpty(referrer)) {
			return false;
		}

		if (Faces.getApplication().getResourceHandler().isResourceURL(referrer)) {
			return false;
		}

		return true;
	}

	// Helpers --------------------------------------------------------------------------------------------------------

	public List<ResourceIdentifier> decodeCombinedResource(String combinedResourceName) {
		return stream(unserializeURLSafe(combinedResourceName.split("\\.")[0]).split("\\|")).map(ResourceIdentifier::new).collect(toList());
	}

	// Getters --------------------------------------------------------------------------------------------------------

	public String getIndex() {
		if (index == null || indexLastLoaded.getDayOfYear() != LocalDate.now().getDayOfYear()) {
			index = loadIndex();
		}

		return index;
	}

	public TreeModel<Page> getMenu() {
		return menu;
	}

	public Collection<Page> getPages() {
		return pages.values();
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