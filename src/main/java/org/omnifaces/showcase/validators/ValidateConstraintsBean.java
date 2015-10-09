package org.omnifaces.showcase.validators;

import javax.inject.Named;

import org.omnifaces.showcase.model.ConstrainedEntity;

@Named
public class ValidateConstraintsBean {

	private ConstrainedEntity constrainedEntity = new ConstrainedEntity();

	public ConstrainedEntity getConstrainedEntity() {
		return constrainedEntity;
	}

}
