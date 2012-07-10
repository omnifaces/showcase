package org.omnifaces.showcase.components;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ViewScoped
@ManagedBean
public class CacheBean {
	
	private List<String> items;
	private int key;

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

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

}
