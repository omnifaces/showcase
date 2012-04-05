package org.omnifaces.showcase;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class Page implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final String INDEX_PATH = "/index.xhtml";
	public static final Page INDEX = new Page("home", INDEX_PATH, null);

	private String title;
	private String path;
	private List<Source> sources;

	@ManagedProperty("#{app.pages[view.viewId]}")
	private Page current;

	public Page() {
		//
	}

	public Page(String title, String path, List<Source> sources) {
		this.title = title;
		this.path = path;
		this.sources = sources;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Source> getSources() {
		return sources;
	}

	public Page getCurrent() {
		return current;
	}

	public void setCurrent(Page current) {
		this.current = current;
	}

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
		return String.format("Page[title=%s,path=%s]", title, path);
	}

	public static class Source {

		private String title;
		private String type;
		private String code;

		public Source(String title, String type, String code) {
			this.title = title;
			this.type = type;
			this.code = code;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

	}

}