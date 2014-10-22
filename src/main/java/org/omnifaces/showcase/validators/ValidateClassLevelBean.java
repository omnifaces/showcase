package org.omnifaces.showcase.validators;

import static org.omnifaces.util.Faces.isValidationFailed;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import org.omnifaces.showcase.model.Product;

@Named
@RequestScoped
public class ValidateClassLevelBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Product product = new Product();
	private String invoked;

	public void action() {
		if (!isValidationFailed()) {
			invoked = "Ok!";
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