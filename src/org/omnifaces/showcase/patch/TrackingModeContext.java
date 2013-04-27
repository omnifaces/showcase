package org.omnifaces.showcase.patch;

import static javax.servlet.SessionTrackingMode.URL;

import javax.faces.context.ExternalContext;
import javax.faces.context.ExternalContextWrapper;
import javax.servlet.ServletContext;

public class TrackingModeContext extends ExternalContextWrapper {

	private final ExternalContext wrapped;

	public TrackingModeContext(ExternalContext wrapped) {
		this.wrapped = wrapped;
	}

	@Override
	public String encodeActionURL(String url) {

		if (getContext() instanceof ServletContext && !((ServletContext) getContext()).getEffectiveSessionTrackingModes().contains(URL)) {
			return url;
		}

		return super.encodeActionURL(url);
	}

	@Override
	public ExternalContext getWrapped() {
		return wrapped;
	}

}
