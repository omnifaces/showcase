package org.omnifaces.showcase.converters;

import static java.util.Arrays.asList;

import java.io.Serializable;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.showcase.model.ExampleEntity;
import org.primefaces.model.DualListModel;

@ManagedBean
@ViewScoped
public class SelectListBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private DualListModel<ExampleEntity> model;

	@PostConstruct
	public void init() {
		model = new DualListModel<>(
			asList(
				new ExampleEntity(1l, "Amsterdam"),
				new ExampleEntity(2l, "Frankfurt"),
				new ExampleEntity(3l, "Berlin")
			),
			new ArrayList<ExampleEntity>()
		);
	}

	public DualListModel<ExampleEntity> getModel() {
		return model;
	}

}