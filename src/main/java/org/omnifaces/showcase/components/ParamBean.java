package org.omnifaces.showcase.components;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

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