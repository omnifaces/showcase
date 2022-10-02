package org.omnifaces.showcase.cdi;

import jakarta.ejb.EJB;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.FacesValidator;
import jakarta.faces.validator.Validator;
import jakarta.faces.validator.ValidatorException;
import jakarta.inject.Inject;

import org.omnifaces.util.Messages;

@FacesValidator("someValidator")
public class SomeValidator implements Validator {

	@EJB
	private SomeEJB ejb;

	@Inject
	private SomeCDI cdi;

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		Messages.addInfo(component.getClientId(context), "Validator currently used: {0}", this);
		Messages.addInfo(component.getClientId(context), "EJB injected in validator: {0}", ejb);
		Messages.addInfo(component.getClientId(context), "CDI injected in validator: {0}", cdi);
	}

}