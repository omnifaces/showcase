package org.omnifaces.showcase.eventlisteners;

import java.io.Serializable;

import jakarta.inject.Named;

import org.omnifaces.cdi.ViewScoped;

@Named
@ViewScoped
public class ResetInputBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String input1;
	private Integer input2;

	public void update() {
		input1 = "Updated!";
		input2 = 42;
	}

	public String getInput1() {
		return input1;
	}

	public void setInput1(String input1) {
		this.input1 = input1;
	}

	public Integer getInput2() {
		return input2;
	}

	public void setInput2(Integer input2) {
		this.input2 = input2;
	}

}