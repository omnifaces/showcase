package org.omnifaces.showcase.components;

import static org.omnifaces.util.Faces.getContext;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.omnifaces.component.output.cache.CacheFactory;

@RequestScoped
@ManagedBean
public class CacheBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<String> items;
	private long key;

	@PostConstruct
	public void init() {
		String date = new Date().toString();
		items = new ArrayList<String>();
		items.add("A - " + date);
		items.add("B - " + date);
		items.add("C - " + date);
	}

	public List<String> getItems() {
		return items;
	}

	public long getKey() {
		return key;
	}

	public void setKey(long key) {
		this.key = key;
	}
	
	public void reset() {
		CacheFactory.getCache(getContext(), "session").remove("firstCache");
	}

}
