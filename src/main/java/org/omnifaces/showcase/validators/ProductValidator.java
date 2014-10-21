package org.omnifaces.showcase.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.omnifaces.showcase.model.Product;

public class ProductValidator implements ConstraintValidator<ValidProduct, Product> {

	@Override
	public void initialize(ValidProduct constraintAnnotation) {
		
	}
	
	@Override
	public boolean isValid(Product value, ConstraintValidatorContext context) {
		return value.getNumber1() < value.getNumber2();
	}
	
}
