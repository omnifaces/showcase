package org.omnifaces.showcase.validators;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.showcase.model.ExampleEntity;

@ManagedBean
@ViewScoped
public class ValidateUniqueColumnBean {

	private List<ExampleEntity> entities;

	@PostConstruct
	public void init() {
		entities = new ArrayList<ExampleEntity>();
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