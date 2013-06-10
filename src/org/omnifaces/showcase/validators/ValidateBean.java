package org.omnifaces.showcase.validators;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@ManagedBean
@ViewScoped
public class ValidateBean implements Serializable {

	private static final long serialVersionUID = 6654746339417292962L;
	
	@Pattern(regexp="^(?=\\s*\\S).*$", message = "Please enter value") // not empty
	@Size(min = 6, groups = MyValidationGroup.class, message = "Please enter at least 6 characters")
	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}