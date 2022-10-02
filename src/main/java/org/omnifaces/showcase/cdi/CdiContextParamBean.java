package org.omnifaces.showcase.cdi;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import org.omnifaces.cdi.ContextParam;

@Named
@RequestScoped
public class CdiContextParamBean {

	@Inject @ContextParam(name="jakarta.faces.FACELETS_BUFFER_SIZE")
	private String faceletsBufferSize;

	@Inject @ContextParam(name="jakarta.faces.FACELETS_LIBRARIES")
	private String faceletsLibraries;

	@Inject @ContextParam(name="jakarta.faces.FACELETS_SKIP_COMMENTS")
	private String faceletsSkipComments;

	public String getFaceletsBufferSize() {
		return faceletsBufferSize;
	}

	public String getFaceletsLibraries() {
		return faceletsLibraries;
	}

	public String getFaceletsSkipComments() {
		return faceletsSkipComments;
	}

}