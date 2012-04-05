package org.omnifaces.showcase;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
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
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.omnifaces.model.tree.ListTreeModel;
import org.omnifaces.model.tree.TreeModel;
import org.omnifaces.showcase.Page.Source;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Utils;

@ManagedBean(eager=true)
@ApplicationScoped
public class App implements Serializable {

	// Constants ------------------------------------------------------------------------------------------------------

	private static final long serialVersionUID = 1L;
	private static final String SHOWCASE_PATH = "/showcase/";
	private static final String SHOWCASE_PROPERTIES_PATH = "/WEB-INF/showcase.properties";

	private static final String ERROR_LOADING_SHOWCASE_PROPERTIES = "Unable to load " + SHOWCASE_PROPERTIES_PATH;
	private static final String ERROR_LOADING_PAGE_SOURCE = "Unable to load source code of %s";
	private static final String ERROR_MISSING_PROPERTY = "Property '%s' is missing in " + SHOWCASE_PROPERTIES_PATH;

	// Properties -----------------------------------------------------------------------------------------------------

	private TreeModel<Page> menu;
	private Map<String, Page> pages;
	private String poweredBy;

	// Initialization -------------------------------------------------------------------------------------------------

	@PostConstruct
	public void init() {
		menu = new ListTreeModel<Page>();
		fillMenu(menu);
		pages = new HashMap<String, Page>();
		fillPages(pages, menu);
		poweredBy = initPoweredBy();
	}

	private static void fillMenu(TreeModel<Page> menu) {
		menu.addChild(Page.INDEX);

		Properties properties = loadProperties();
		ServletContext context = Faces.getServletContext();
		Set<String> groupPaths = new TreeSet<String>(context.getResourcePaths(SHOWCASE_PATH));

		for (String groupPath : groupPaths) {
			TreeModel<Page> group = menu.addChild(new Page(groupPath.split("/")[2], null, null));
			Set<String> pagePaths = new TreeSet<String>(context.getResourcePaths(groupPath));

			for (String pagePath : pagePaths) {
				group.addChild(createPage(properties, pagePath));
			}
		}
	}

	private static Properties loadProperties() {
		Properties properties = new Properties();

		try {
			properties.load(Faces.getResourceAsStream(SHOWCASE_PROPERTIES_PATH));
		}
		catch (IOException e) {
			throw new FacesException(ERROR_LOADING_SHOWCASE_PROPERTIES, e);
		}

		return properties;
	}

	private static Page createPage(Properties properties, String pagePath) {
		String pageName = pagePath.split("/")[3].split("\\.")[0];
		List<Source> pageSources = new ArrayList<Source>();

		pageSources.add(new Source("Example", "xhtml", loadSourceCode(pagePath)
			.split("<ui:define name=\"example\">")[1]
			.split("</ui:define>")[0]
			.replace("\n        ", "\n") // Trim 8 leading spaces so that the whole block is indented back.
			.trim()));

		String propertyKey = "sources." + pageName;
		String sourceKeys = properties.getProperty(propertyKey);

		if (sourceKeys != null) {
			for (String sourceKey : sourceKeys.split("\\s*,\\s*")) {
				pageSources.add(createSource(properties, propertyKey + "." + sourceKey));
			}
		}

		return new Page(pageName, pagePath, pageSources);
	}

	private static String loadSourceCode(String path) {
		InputStream input = Faces.getResourceAsStream(path);

		if (input == null) {
			return "Source code is not available at " + path;
		}

		try {
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			Utils.stream(input, output);
			return new String(output.toByteArray(), "UTF-8").replace("\t", "    "); // Tabs are in HTML <pre> presented as 8 spaces by the average browser, which is too much.
		}
		catch (IOException e) {
			throw new FacesException(String.format(ERROR_LOADING_PAGE_SOURCE, path), e);
		}
	}

	private static Source createSource(Properties properties, String sourceKey) {
		String sourceTitle = getMandatoryProperty(properties, sourceKey + ".title");
		String sourcePath = getMandatoryProperty(properties, sourceKey + ".path");
		String sourceType = sourcePath.substring(sourcePath.lastIndexOf('.') + 1);
		String sourceCode = loadSourceCode(sourcePath);
		return new Source(sourceTitle, sourceType, sourceCode);
	}

	private static String getMandatoryProperty(Properties properties, String key) {
		String property = properties.getProperty(key);

		if (property == null) {
			throw new IllegalArgumentException(String.format(ERROR_MISSING_PROPERTY, key));
		}

		return property;
	}

	private static void fillPages(Map<String, Page> pages, TreeModel<Page> menu) {
		for (TreeModel<Page> node : menu) {
			Page page = node.getData();
			pages.put(page.getPath(), page);
			fillPages(pages, node);
		}
	}

	private static String initPoweredBy() {
		Package jsfPackage = FacesContext.class.getPackage();
		return String.format("%s %s%nOmniFaces %s%nPrimeFaces %s%n%s",
			jsfPackage.getImplementationTitle(), jsfPackage.getImplementationVersion(),
			"0.06", // TODO: Hardcoded :( Let's do the same as Mojarra; it's done by MANIFEST.MF.
			org.primefaces.util.Constants.VERSION,
			Faces.getServletContext().getServerInfo());
	}

	// Getters --------------------------------------------------------------------------------------------------------

	public TreeModel<Page> getMenu() {
		return menu;
	}

	public Map<String, Page> getPages() {
		return pages;
	}

	public String getPoweredBy() {
		return poweredBy;
	}

}