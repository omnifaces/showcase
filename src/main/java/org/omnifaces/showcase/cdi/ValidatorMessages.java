package org.omnifaces.showcase.cdi;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class ValidatorMessages {
	
	public String getRequiredMsg() {
		return "{1}: You really have to provide a value here!";
	}

}
