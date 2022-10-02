package org.omnifaces.showcase.cdi;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

@Named
@RequestScoped
public class ValidatorMessages {
	
	public String getRequiredMsg() {
		return "{1}: You really have to provide a value here!";
	}

}
