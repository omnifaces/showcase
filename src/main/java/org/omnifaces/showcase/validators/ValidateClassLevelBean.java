package org.omnifaces.showcase.validators;

import static org.omnifaces.util.Faces.isValidationFailed;
import static org.omnifaces.util.Messages.addGlobalInfo;
import static org.omnifaces.util.Messages.addGlobalWarn;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

import org.omnifaces.showcase.model.Product;

@Named
@RequestScoped
public class ValidateClassLevelBean {

	private Product product;

	@PostConstruct
	public void init() {
		product = new Product();
	}

	public void action() {
		if (isValidationFailed()) {
			addGlobalWarn("Validation failed, but action method invoked.");
		}
		else {
			addGlobalInfo("OK! Validation has not failed.");
		}
	}

	public Product getProduct() {
		return product;
	}

}