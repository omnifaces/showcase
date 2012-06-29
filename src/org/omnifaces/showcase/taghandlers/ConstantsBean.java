package org.omnifaces.showcase.taghandlers;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.omnifaces.showcase.model.ExampleEnum;

@ManagedBean
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