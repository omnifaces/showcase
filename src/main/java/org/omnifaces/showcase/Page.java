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

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.omnifaces.model.tree.ListTreeModel;

/**
 * This class represents a page. All pages are available as a tree model by {@link App#getMenu()} and are by their view
 * ID collected in a mapping as available by {@link App#getPages()}. The current page is injected from the mapping
 * based on the current view ID.
 *
 * @author Bauke Scholtz
 */
@ManagedBean
@RequestScoped
public class Page extends ListTreeModel<Page> {

	// Constants ------------------------------------------------------------------------------------------------------

	private static final long serialVersionUID = 1L;

	// Properties -----------------------------------------------------------------------------------------------------

	private String title;
	private String viewId;
	private List<Source> sources;
	private Documentation documentation;

	@ManagedProperty("#{app.pages[view.viewId]}")
	private Page current;

	// Constructors ---------------------------------------------------------------------------------------------------

	public Page() {
		// Keep default c'tor alive for JSF @ManagedBean.
	}

	public Page(String title, String viewId, List<Source> sources, Documentation documentation) {
		this.title = title;
		this.viewId = viewId;
		this.sources = sources;
		this.documentation = documentation;
	}

	// Getters/setters ------------------------------------------------------------------------------------------------

	public String getTitle() {
		return title;
	}

	public String getViewId() {
		return viewId;
	}

	public List<Source> getSources() {
		return sources;
	}

	public Documentation getDocumentation() {
		return documentation;
	}

	public Page getCurrent() {
		return current;
	}

	public void setCurrent(Page current) {
		this.current = current;
	}

	// Object overrides -----------------------------------------------------------------------------------------------

	@Override
	public boolean equals(Object other) {
		return (other instanceof Page) && (title != null)
			? title.equals(((Page) other).title)
			: (other == this);
	}

	@Override
	public int hashCode() {
		return (title != null)
			? (getClass().hashCode() + title.hashCode())
			: super.hashCode();
	}

	@Override
	public String toString() {
		return String.format("Page[title=%s,viewId=%s]", title, viewId);
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
	 * This class represents the documentation associated with the current page.
	 *
	 * @author Bauke Scholtz
	 */
	public static class Documentation implements Serializable {

		// Constants --------------------------------------------------------------------------------------------------

		private static final long serialVersionUID = 1L;

		// Properties -------------------------------------------------------------------------------------------------

		private String[] vdldocs;
		private String[] apidocs;
		private String[] codes;

		// Contructors ------------------------------------------------------------------------------------------------

		public Documentation(String[] vdldocs, String[] apidocs, String[] codes) {
			this.vdldocs = vdldocs;
			this.apidocs = apidocs;
			this.codes = codes;
		}

		// Getters/setters --------------------------------------------------------------------------------------------

		public String[] getVdldocs() {
			return vdldocs;
		}

		public String[] getApidocs() {
			return apidocs;
		}

		public String[] getCodes() {
			return codes;
		}

	}

}