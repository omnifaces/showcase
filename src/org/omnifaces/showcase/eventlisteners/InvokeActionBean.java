package org.omnifaces.showcase.eventlisteners;

import static org.omnifaces.facesviews.FacesViewsUtils.stripExtension;
import static org.omnifaces.util.Faces.getRequestURI;
import static org.omnifaces.util.Faces.redirect;
import static org.omnifaces.util.Utils.isEmpty;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ComponentSystemEvent;

import org.omnifaces.util.Messages;

@ManagedBean
@ViewScoped
public class InvokeActionBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String param;
	private String input;

	public void initParam() throws IOException {
		if (!isEmpty(param)) {
			Messages.addFlashInfo("param", "Param \"{0}\" is successfully set and you have been redirected!", param);
			redirect(stripExtension(getRequestURI()));
		}
	}

	public void inputListener() {
		Messages.addGlobalInfo("Input entered so far: {0}", input);
	}

	public void submit() {
		Messages.addGlobalInfo("Form submitted with input: {0}", input);
	}

	public void preInvokeAction(ComponentSystemEvent event) {
		Messages.addGlobalInfo("preInvokeAction event executed on component: {0}", event.getComponent());
	}

	public void postInvokeAction(ComponentSystemEvent event) {
		Messages.addGlobalInfo("postInvokeAction event executed on component: {0}", event.getComponent());
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}

}