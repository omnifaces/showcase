package org.omnifaces.showcase.components;

import static org.omnifaces.util.Utils.coalesce;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class ComponentParamBean {

	private String input;
	private List<String> tableItems;

	@ManagedProperty("#{param.start}")
	private Integer start;
	private List<String> listItems;
	private String selectedItem;

	@PostConstruct
	public void init() {
		// Fill table items.
		tableItems = Arrays.asList("row1", "row2", "row3");

		// Fill list items.
		listItems = new ArrayList<>();

		// Liberty 8.5.5/MyFaces 2.0.5 calls setStart AFTER this @PostContruct method is called,
		// causing a NPE.
		int size = getStart() + 3;

		for (int i = getStart(); i < size; i++) {
			listItems.add("item " + (i + 1));
		}
	}

	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}

	public List<String> getTableItems() {
		return tableItems;
	}

	// Those coalesce()s are necessary because they cannot be declared as int as MyFaces would throw NPE
	// when the request parameter is absent (Mojarra auto-coerces it to 0).

	public void setStart(Integer start) {
		this.start = coalesce(start, 0);
	}

	public Integer getStart() {
		return coalesce(start, 0);
	}

	public List<String> getListItems() {
		return listItems;
	}

	public String getSelectedItem() {
		return selectedItem;
	}

	public void setSelectedItem(String selectedItem) {
		this.selectedItem = selectedItem;
	}

}