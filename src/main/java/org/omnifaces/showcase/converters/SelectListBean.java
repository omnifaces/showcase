package org.omnifaces.showcase.converters;

import static java.util.Arrays.asList;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.annotation.PostConstruct;
import jakarta.inject.Named;

import org.omnifaces.cdi.ViewScoped;
import org.omnifaces.showcase.model.ExampleEntity;
import org.primefaces.model.DualListModel;

@Named
@ViewScoped
public class SelectListBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<ExampleEntity> fullList;
	private DualListModel<ExampleEntity> model;

	@PostConstruct
	public void init() {

		fullList = asList(
			new ExampleEntity(1l, "Amsterdam"),
			new ExampleEntity(2l, "Frankfurt"),
			new ExampleEntity(3l, "Berlin")
		);

		model = new DualListModel<>(
			new ArrayList<>(fullList),
			new ArrayList<ExampleEntity>()
		);
	}

	public List<ExampleEntity> getFullList() {
		return fullList;
	}

	public DualListModel<ExampleEntity> getModel() {
		return model;
	}

	public void setModel(DualListModel<ExampleEntity> model) {
		this.model = model;
	}

}