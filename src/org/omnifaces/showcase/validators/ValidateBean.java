package org.omnifaces.showcase.validators;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

@ManagedBean
@ViewScoped
public class ValidateBean {

	@NotEmpty(message = "Please enter value")
	@Length(min = 6, groups = MyValidationGroup.class, message = "Please enter at least 6 characters")
	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}