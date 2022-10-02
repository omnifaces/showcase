package org.omnifaces.showcase.cdi;

import java.io.Serializable;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.faces.application.FacesMessage;
import jakarta.inject.Named;

import org.omnifaces.cdi.ViewScoped;
import org.omnifaces.cdi.viewscope.ViewScopeManager;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

@Named
@ViewScoped
public class CdiViewScopedBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@PostConstruct
	public void postConstruct() {
		FacesMessage unloadMessage = Faces.removeSessionAttribute("unloadMessage");

		if (unloadMessage != null) {
			Messages.add("cdiViewScopedForm", unloadMessage);
		}

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
    		if (ViewScopeManager.isUnloadRequest(Faces.getContext())) {
        		Faces.setSessionAttribute("unloadMessage", Messages.createInfo("PreDestroy invoked during unload: {0}", this));
    		}
    		else {
        		Messages.addInfo("cdiViewScopedForm", "PreDestroy invoked during postback: {0}", this);
    		}
    	}
    }

}