package org.omnifaces.showcase.cdi;

import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;

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