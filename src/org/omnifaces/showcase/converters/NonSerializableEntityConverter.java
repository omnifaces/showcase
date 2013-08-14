package org.omnifaces.showcase.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.omnifaces.showcase.model.NonSerializableEntity;

@FacesConverter("nonSerializableEntityConverter")
public class NonSerializableEntityConverter implements Converter {
	
	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value instanceof NonSerializableEntity) {
			return ((NonSerializableEntity) value).getValue();
		}
		
		return null;
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		return new NonSerializableEntity(value);
	}
	

}
