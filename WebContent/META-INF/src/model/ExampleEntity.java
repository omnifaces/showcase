package org.omnifaces.showcase.model;

public class ExampleEntity {

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