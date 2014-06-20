package org.omnifaces.showcase.components;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.omnifaces.showcase.model.ExampleEntity;

@ManagedBean
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