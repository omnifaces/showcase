package org.omnifaces.showcase.model;

public class NonSerializableEntity {

	private String value;

	public NonSerializableEntity(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return value;
	}

}