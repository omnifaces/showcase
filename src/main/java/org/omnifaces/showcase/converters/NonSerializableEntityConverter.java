package org.omnifaces.showcase.converters;

import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;

import org.omnifaces.showcase.model.NonSerializableEntity;

@FacesConverter("nonSerializableEntityConverter")
public class NonSerializableEntityConverter implements Converter<NonSerializableEntity> {

	@Override
	public String getAsString(FacesContext context, UIComponent component, NonSerializableEntity value) {
		return value != null ? value.getValue() : "";
	}

	@Override
	public NonSerializableEntity getAsObject(FacesContext context, UIComponent component, String value) {
		return value != null ? new NonSerializableEntity(value) : null;
	}

}