package org.omnifaces.showcase.cdi;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.cdi.ContextParam;

@Named
@RequestScoped
public class CdiContextParamBean {

	@Inject @ContextParam(name="javax.faces.FACELETS_BUFFER_SIZE")
	private String faceletsBufferSize;

	@Inject @ContextParam(name="javax.faces.FACELETS_LIBRARIES")
	private String faceletsLibraries;

	@Inject @ContextParam(name="javax.faces.FACELETS_SKIP_COMMENTS")
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