package org.omnifaces.showcase.converters;

import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;

import org.omnifaces.showcase.model.NonSerializableEntity;

@FacesConverter("nonSerializableEntityConverter")
public class NonSerializableEntityConverter implements Converter {

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value instanceof NonSerializableEntity) {
			return ((NonSerializableEntity) value).getValue();
		}

		return "";
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		return new NonSerializableEntity(value);
	}

}