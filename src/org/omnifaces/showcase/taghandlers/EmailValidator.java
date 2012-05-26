package org.omnifaces.showcase.taghandlers;

import java.util.regex.Pattern;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.omnifaces.util.Messages;

@FacesValidator("emailValidator")
public class EmailValidator implements Validator {

	private static final Pattern PATTERN = Pattern.compile("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)");

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		if (value == null) {
			return; // Let required="true" handle.
		}

		if (!PATTERN.matcher(value.toString()).matches()) {
			throw new ValidatorException(Messages.createError("Invalid email format"));
		}
	}

}