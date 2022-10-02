package org.omnifaces.showcase.taghandlers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.annotation.PostConstruct;
import jakarta.inject.Named;

import org.omnifaces.cdi.ViewScoped;
import org.omnifaces.showcase.model.ExampleEntity;

@Named
@ViewScoped
public class SkipValidatorsBean implements Serializable {

	private List<ExampleEntity> items;

	@PostConstruct
	public void init() {
		items = new ArrayList<>();
		add();
	}

	public void add() {
		items.add(new ExampleEntity());
	}

	public List<ExampleEntity> getItems() {
		return items;
	}

}
