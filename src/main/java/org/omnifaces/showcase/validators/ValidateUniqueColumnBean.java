package org.omnifaces.showcase.validators;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.annotation.PostConstruct;
import jakarta.inject.Named;

import org.omnifaces.cdi.ViewScoped;
import org.omnifaces.showcase.model.ExampleEntity;

@Named
@ViewScoped
public class ValidateUniqueColumnBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<ExampleEntity> entities;

	@PostConstruct
	public void init() {
		entities = new ArrayList<>();
		entities.add(new ExampleEntity(1L, "one"));
		entities.add(new ExampleEntity(2L, "two"));
		entities.add(new ExampleEntity(3L, "three"));
		entities.add(new ExampleEntity(4L, "four"));
		entities.add(new ExampleEntity(5L, "five"));
	}

	public List<ExampleEntity> getEntities() {
		return entities;
	}

}