package org.omnifaces.showcase.cdi;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

@Named
@ViewScoped
public class CdiViewScopedBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@PostConstruct
	public void postConstruct() {
		Messages.addInfo("cdiViewScopedForm", "PostConstruct invoked: {0}", this);
	}

	public void submit() {
		Messages.addInfo("cdiViewScopedForm", "Submit invoked: {0}", this);
	}

	public String navigate() {
		Messages.addInfo("cdiViewScopedForm", "Navigate on POST invoked: {0}", this);
		return Faces.getViewId();
	}

    public void rebuildView() {
		Messages.addInfo("cdiViewScopedForm", "Rebuild view invoked: {0}", this);
    	Faces.setViewRoot(Faces.getViewId());
    }

    @PreDestroy
    public void preDestroy() {
    	if (Faces.getContext() != null) { // It can be null during session invalidate!
    		Messages.addInfo("cdiViewScopedForm", "PreDestroy invoked: {0}", this);
    	}
    }

}