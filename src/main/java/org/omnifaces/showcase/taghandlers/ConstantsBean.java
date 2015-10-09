package org.omnifaces.showcase.taghandlers;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import org.omnifaces.showcase.model.ExampleEnum;

@Named
@RequestScoped
public class ConstantsBean {

	public static final String CONSTANT1 = "Constant one";
	public static final String CONSTANT2 = "Constant two";

	private ExampleEnum exampleEnum;

	public ExampleEnum getExampleEnum() {
		return exampleEnum;
	}

	public void setExampleEnum(ExampleEnum exampleEnum) {
		this.exampleEnum = exampleEnum;
	}

}