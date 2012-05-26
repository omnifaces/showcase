package org.omnifaces.showcase.taghandlers;

import java.util.regex.Pattern;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.omnifaces.util.Messages;

@FacesValidator("phoneValidator")
public class PhoneValidator implements Validator {

	private static final Pattern PATTERN_REMOVE = Pattern.compile("[\\s()+\\-\\.]|ext", Pattern.CASE_INSENSITIVE);
	private static final Pattern PATTERN_MATCH = Pattern.compile("\\d{7,}");

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		if (value == null) {
			return; // Let required="true" handle.
		}

		if (!PATTERN_MATCH.matcher(PATTERN_REMOVE.matcher(value.toString()).replaceAll("")).matches()) {
			throw new ValidatorException(Messages.createError("Invalid phone format"));
		}
	}

}