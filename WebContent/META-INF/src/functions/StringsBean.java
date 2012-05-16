package org.omnifaces.showcase.functions;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class StringsBean {

	private String string1 = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.";
	private String string2 = "left";
	private String string3 = "right";
	private String string4 = "STRING with díãçrïtìcs";

	public String getString1() {
		return string1;
	}

	public String getString2() {
		return string2;
	}

	public String getString3() {
		return string3;
	}

	public String getString4() {
		return string4;
	}

}