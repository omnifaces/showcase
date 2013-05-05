package org.omnifaces.showcase.components;

import static org.omnifaces.util.Faces.getContext;
import static org.omnifaces.util.Messages.addGlobalInfo;

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
	private String item;

	@PostConstruct
	public void init() {
		String date = new Date().toString();
		items = new ArrayList<String>();
		items.add("A - " + date);
		items.add("B - " + date);
		items.add("C - " + date);
	}
	
	public void reset() {
		CacheFactory.getCache(getContext(), "session").remove("firstCache");
	}
	
	public void saveItem(String item) {
		this.item = item;
		addGlobalInfo("Item {0} was posted back", item);
	}
	
	public List<String> getItems() {
		return items;
	}
	
	public String getItem() {
		return item;
	}

	public long getKey() {
		return key;
	}

	public void setKey(long key) {
		this.key = key;
	}

}
