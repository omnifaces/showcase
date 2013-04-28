package org.omnifaces.showcase.validators;

import javax.faces.bean.ManagedBean;

import org.omnifaces.showcase.model.ConstrainedEntity;

@ManagedBean
public class ValidateConstraintsBean {

	private ConstrainedEntity constrainedEntity = new ConstrainedEntity();

	public ConstrainedEntity getConstrainedEntity() {
		return constrainedEntity;
	}
	
}
