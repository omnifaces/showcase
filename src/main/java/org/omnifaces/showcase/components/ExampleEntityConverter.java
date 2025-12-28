package org.omnifaces.showcase.components;

import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.ConverterException;
import jakarta.faces.convert.FacesConverter;

import org.omnifaces.showcase.model.ExampleEntity;
import org.omnifaces.util.Messages;

@FacesConverter("exampleEntityConverter")
public class ExampleEntityConverter implements Converter<ExampleEntity> {

	@Override
	public String getAsString(FacesContext context, UIComponent component, ExampleEntity modelValue) {
		Long id = (modelValue != null) ? modelValue.getId() : null;
		return (id != null) ? String.valueOf(id) : "";
	}

	@Override
	public ExampleEntity getAsObject(FacesContext context, UIComponent component, String submittedValue) {
		if (submittedValue == null || submittedValue.isEmpty()) {
			return null;
		}

		if (!submittedValue.matches("[0-9]+")) {
			throw new ConverterException(Messages.createError("Invalid ID."));
		}

		Long id = Long.valueOf(submittedValue);

		if (id != 42) {
			throw new ConverterException(Messages.createError("Unknown ID."));
		}

		return new ExampleEntity(id, "The meaning of life");
	}

}