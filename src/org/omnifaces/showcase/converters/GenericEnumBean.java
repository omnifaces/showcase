package org.omnifaces.showcase.converters;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.showcase.model.ExampleEnum;

@ManagedBean
@ViewScoped
public class GenericEnumBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private ExampleEnum[] enumArray;
	private List<ExampleEnum> enumList;
	private List<ExampleEnum> enumListConverted;

	public ExampleEnum[] getEnumArray() {
		return enumArray;
	}

	public void setEnumArray(ExampleEnum[] enumArray) {
		this.enumArray = enumArray;
	}

	public List<ExampleEnum> getEnumList() {
		return enumList;
	}

	public void setEnumList(List<ExampleEnum> enumList) {
		this.enumList = enumList;
	}

	public List<ExampleEnum> getEnumListConverted() {
		return enumListConverted;
	}

	public void setEnumListConverted(List<ExampleEnum> enumListConverted) {
		this.enumListConverted = enumListConverted;
	}

}