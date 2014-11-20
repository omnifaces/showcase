package org.omnifaces.showcase.components;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.Part;

import org.omnifaces.el.functions.Numbers;
import org.omnifaces.util.Messages;

@FacesValidator("uploadFileSizeValidator")
public class UploadFileSizeValidator implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		Long fileSize = ((Part) value).getSize();
		Long maxSize = Long.valueOf((String) component.getAttributes().get("maxSize"));

		if (fileSize > maxSize) {
			throw new ValidatorException(
				Messages.createError("File too large! Should be no more than {0}.", Numbers.formatBytes(maxSize)));
		}
	}

}