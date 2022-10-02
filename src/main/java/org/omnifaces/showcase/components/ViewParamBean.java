package org.omnifaces.showcase.components;

import java.io.Serializable;

import jakarta.inject.Named;

import org.omnifaces.cdi.ViewScoped;

@Named
@ViewScoped
public class ViewParamBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String foo;
	private Long bar;

	public String getFoo() {
		return foo;
	}

	public void setFoo(String foo) {
		this.foo = foo;
	}

	public Long getBar() {
		return bar;
	}

	public void setBar(Long bar) {
		this.bar = bar;
	}

}