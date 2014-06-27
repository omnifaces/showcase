package org.omnifaces.showcase.converters;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import org.omnifaces.showcase.model.ExampleEntity;
import org.omnifaces.util.selectitems.SelectItemsBuilder;

@ManagedBean
@ViewScoped
public class SelectItemsBean implements Serializable {

	private static final long serialVersionUID = 1L;

	// 3 different forms of the defining data to which SelectItems can bind.
	private List<ExampleEntity> exampleEntities;
	private List<SelectItem> selectItems;
	private SelectItem[] selectItemArray;

	private ExampleEntity selectedEntity;

	@PostConstruct
	public void init() {
		exampleEntities = new ArrayList<>();
		exampleEntities.add(new ExampleEntity(1L, "Amsterdam"));
		exampleEntities.add(new ExampleEntity(2L, "Frankfurt"));
		exampleEntities.add(new ExampleEntity(3L, "London"));

		selectItems = new SelectItemsBuilder()
							.add(new ExampleEntity(4L, "New York"), "New York")
							.add(new ExampleEntity(5L, "Miami"), "Miami")
							.add(new ExampleEntity(6L, "Los Angeles"), "Los Angeles")
							.buildList();

		selectItemArray = new SelectItemsBuilder()
							.add(new ExampleEntity(7L, "Willemstad"), "Willemstad")
							.add(new ExampleEntity(8L, "Oranjestad"), "Oranjestad")
							.add(new ExampleEntity(9L, "Kralendijk"), "Kralendijk")
							.build();
	}

	public List<ExampleEntity> getExampleEntities() {
		return exampleEntities;
	}

	public List<SelectItem> getSelectItems() {
		return selectItems;
	}

	public SelectItem[] getSelectItemArray() {
		return selectItemArray;
	}

	public ExampleEntity getSelectedEntity() {
		return selectedEntity;
	}

	public void setSelectedEntity(ExampleEntity selectedEntity) {
		this.selectedEntity = selectedEntity;
	}

}