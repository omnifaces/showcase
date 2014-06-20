package org.omnifaces.showcase.model;

import javax.validation.constraints.Size;

public class ConstrainedEntity {
	
	@Size(min = 0, max = 2)
	public String foo;
	
	@Size(min = 0, max = 3)
	public String bar;

	public String getFoo() {
		return foo;
	}

	public void setFoo(String foo) {
		this.foo = foo;
	}

	public String getBar() {
		return bar;
	}

	public void setBar(String bar) {
		this.bar = bar;
	}
	
	

}
