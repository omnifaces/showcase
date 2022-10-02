package org.omnifaces.showcase.cdi;

import jakarta.ejb.EJB;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import jakarta.inject.Inject;

import org.omnifaces.util.Messages;

@FacesConverter("someConverter")
public class SomeConverter implements Converter {

	@EJB
	private SomeEJB ejb;

	@Inject
	private SomeCDI cdi;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Messages.addInfo(component.getClientId(context), "Converter currently used: {0}", this);
		Messages.addInfo(component.getClientId(context), "EJB injected in converter: {0}", ejb);
		Messages.addInfo(component.getClientId(context), "CDI injected in converter: {0}", cdi);
		return value;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		return value != null ? value.toString() : "";
	}

}