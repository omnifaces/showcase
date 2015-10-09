package org.omnifaces.showcase.validators;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.omnifaces.validator.MultiFieldValidator;

@Named
@ApplicationScoped
public class ValidateMultipleImpl implements MultiFieldValidator {

	@Override
	public boolean validateValues(FacesContext context, List<UIInput> components, List<Object> values) {
		return "one".equals(values.get(0)) && "two".equals(values.get(1)) && "three".equals(values.get(2));
	}

}
