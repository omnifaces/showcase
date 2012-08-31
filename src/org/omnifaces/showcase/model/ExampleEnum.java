package org.omnifaces.showcase.model;

public enum ExampleEnum {

	ONE, TWO, THREE;

	public String getFriendlyName() {
		return name().charAt(0) + name().substring(1).toLowerCase();
	}

}