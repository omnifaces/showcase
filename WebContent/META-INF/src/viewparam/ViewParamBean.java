package org.omnifaces.showcase.viewparam;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class ViewParamBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String test;

	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}

	public void action() {
		//
	}

}
