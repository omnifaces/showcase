package org.omnifaces.showcase.taghandlers;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.showcase.model.ExampleEntity;

@ManagedBean
@ViewScoped
public class ValidatorBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String contactMethod1;
	private String contactMethod2;
	private List<ExampleEntity> entities;

	@PostConstruct
	public void init() {
		contactMethod1 = contactMethod2 = "email";
		entities = Arrays.asList(new ExampleEntity(), new ExampleEntity(), new ExampleEntity());
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

	public List<ExampleEntity> getEntities() {
		return entities;
	}

}