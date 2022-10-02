package org.omnifaces.showcase.components;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

import org.omnifaces.showcase.model.ExampleEntity;

@Named
@RequestScoped
public class ParamBean {

	private ExampleEntity exampleEntity;

	@PostConstruct
	public void init() {
		exampleEntity = new ExampleEntity(42L, "The meaning of life");
	}

	public ExampleEntity getExampleEntity() {
		return exampleEntity;
	}

}