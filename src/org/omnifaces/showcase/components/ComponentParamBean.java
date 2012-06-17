package org.omnifaces.showcase.components;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

@ManagedBean
public class ComponentParamBean {

	private List<String> items;
	private String boo;

	@PostConstruct
	public void init() {
		items = new ArrayList<String>();
		items.add("A");
		items.add("B");
		items.add("C");
	}

	public void doAction() {
	}

	public List<String> getItems() {
		return items;
	}

	public String getBoo() {
		return boo;
	}

	public void setBoo(String boo) {
		this.boo = boo;
	}

}
