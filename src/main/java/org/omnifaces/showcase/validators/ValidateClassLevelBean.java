package org.omnifaces.showcase.validators;

import static org.omnifaces.util.Faces.isValidationFailed;

import java.io.Serializable;

import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;
import org.omnifaces.showcase.model.Product;

@Named
@ViewScoped
public class ValidateClassLevelBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Product product = new Product();
	private String invoked;

	public void action() {
		if (!isValidationFailed()) {
			invoked = "Ok!";
		} else {
			invoked = "Validation failed, but action method invoked.";
		}
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getInvoked() {
		return invoked;
	}

}