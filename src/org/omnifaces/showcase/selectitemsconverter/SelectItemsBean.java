package org.omnifaces.showcase.selectitemsconverter;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;

import org.omnifaces.util.selectitems.SelectItemsBuilder;

@ManagedBean
public class SelectItemsBean {

	// 3 different forms of the defining data to which SelectItems can bind.
	private List<ExampleEntity> exampleEntities;
	private List<SelectItem> selectItems;
	private SelectItem[] selectItemArray;

	private ExampleEntity selectedEntity;

	@PostConstruct
	public void init() {
		exampleEntities = new ArrayList<ExampleEntity>();
		exampleEntities.add(new ExampleEntity("Amsterdam", 1));
		exampleEntities.add(new ExampleEntity("Frankfurt", 2));
		exampleEntities.add(new ExampleEntity("London", 3));

		selectItems = new SelectItemsBuilder()
							.add(new ExampleEntity("Amsterdam", 1), "Amsterdam")
							.add(new ExampleEntity("Frankfurt", 2), "Frankfurt")
							.add(new ExampleEntity("London", 3), "London")
							.buildList();

		selectItemArray = new SelectItemsBuilder()
							.add(new ExampleEntity("Amsterdam", 1), "Amsterdam")
							.add(new ExampleEntity("Frankfurt", 2), "Frankfurt")
							.add(new ExampleEntity("London", 3), "London")
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
