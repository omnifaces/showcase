package org.omnifaces.showcase.cdi;

import java.io.Serializable;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

@Named
@ViewScoped
public class JsfViewScopedBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@PostConstruct
	public void postConstruct() {
		Messages.addInfo("jsfViewScopedForm", "PostConstruct invoked: {0}", this);
	}

	public void submit() {
		Messages.addInfo("jsfViewScopedForm", "Submit invoked: {0}", this);
	}

	public String navigate() {
		Messages.addInfo("jsfViewScopedForm", "Navigate on POST invoked: {0}", this);
		return Faces.getViewId();
	}

    public void rebuildView() {
		Messages.addInfo("jsfViewScopedForm", "Rebuild view invoked: {0}", this);
    	Faces.setViewRoot(Faces.getViewId());
    }

    @PreDestroy
    public void preDestroy() {
    	if (Faces.getContext() != null) { // It can be null during session invalidate!
        	Messages.addInfo("jsfViewScopedForm", "PreDestroy invoked: {0}", this);
    	}
    }

}