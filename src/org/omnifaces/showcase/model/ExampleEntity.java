package org.omnifaces.showcase.model;

import java.io.Serializable;

public class ExampleEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String value;

	public ExampleEntity() {
		//
	}

	public ExampleEntity(Long id, String value) {
		this.id = id;
		this.value = value;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return String.valueOf(id);
	}

}