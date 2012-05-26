package org.omnifaces.showcase.taghandlers;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class ValidatorBean {

	private String contactMethod;

	@PostConstruct
	public void init() {
		contactMethod = "email";
	}

	public String getContactMethod() {
		return contactMethod;
	}

	public void setContactMethod(String contactMethod) {
		this.contactMethod = contactMethod;
	}

}