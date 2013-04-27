package org.omnifaces.showcase.patch;

import javax.faces.FacesException;
import javax.faces.context.ExternalContext;
import javax.faces.context.ExternalContextFactory;

public class TrackingModeExternalContextFactory extends ExternalContextFactory {

	private ExternalContextFactory delegate;

	public TrackingModeExternalContextFactory(ExternalContextFactory delegate) {
		this.delegate = delegate;
	}

	@Override
	public ExternalContext getExternalContext(Object context, Object request, Object response) throws FacesException {
		return new TrackingModeContext(delegate.getExternalContext(context, request, response));
	}

}
