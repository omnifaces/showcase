package org.omnifaces.showcase.converters;

import java.io.Serializable;

import jakarta.inject.Named;

import org.omnifaces.cdi.ViewScoped;
import org.omnifaces.showcase.model.ExampleEnum;

@Named
@ViewScoped
public class GenericEnumBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private ExampleEnum[] enumArray;

	public ExampleEnum[] getEnumArray() {
		return enumArray;
	}

	public void setEnumArray(ExampleEnum[] enumArray) {
		this.enumArray = enumArray;
	}

}