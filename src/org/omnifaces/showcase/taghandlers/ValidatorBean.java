package org.omnifaces.showcase.taghandlers;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class ValidatorBean {

	private String contactMethod1;
	private String contactMethod2;

	@PostConstruct
	public void init() {
		contactMethod1 = contactMethod2 = "email";
	}

	public String getContactMethod1() {
		return contactMethod1;
	}

	public void setContactMethod1(String contactMethod1) {
		this.contactMethod1 = contactMethod1;
	}

	public String getContactMethod2() {
		return contactMethod2;
	}

	public void setContactMethod2(String contactMethod2) {
		this.contactMethod2 = contactMethod2;
	}

}