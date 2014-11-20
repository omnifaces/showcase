package org.omnifaces.showcase.components;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.Part;

import org.omnifaces.util.Messages;

@FacesValidator("uploadFileTypeValidator")
public class UploadFileTypeValidator implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		String contentType = ((Part) value).getContentType();
		String matchType = (String) component.getAttributes().get("matchType");

		if (!contentType.matches(matchType)) {
			throw new ValidatorException(
				Messages.createError("Wrong file type! Should match {0}.", matchType));
		}
	}

}