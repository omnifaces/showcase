package org.omnifaces.showcase.validators;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

import org.omnifaces.showcase.model.ConstrainedEntity;

@Named
@RequestScoped
public class ValidateConstraintsBean {

	private ConstrainedEntity constrainedEntity = new ConstrainedEntity();

	public ConstrainedEntity getConstrainedEntity() {
		return constrainedEntity;
	}

}
