package org.omnifaces.showcase.taghandlers;

import javax.enterprise.context.RequestScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Named;

@Named
@RequestScoped
public class MethodParamBean {

	private boolean actionInvoked;
	private boolean listenerInvoked;
	private boolean listenerWithEventInvoked;

	public void doAction() {
		actionInvoked = true;
	}

	public void doActionWithParam(String dummy) {
		actionInvoked = true;
	}

	public void actionListener() {
		listenerInvoked = true;
	}

	public void actionListenerWithParam(String dummy) {
		listenerInvoked = true;
	}

	public void actionListenerWithClientParam(ActionEvent event) {
		listenerWithEventInvoked = true;
	}

	public MethodParamBean getBean() {
		return this;
	}

	public boolean getActionInvoked() {
		return actionInvoked;
	}

	public boolean getListenerInvoked() {
		return listenerInvoked;
	}

	public boolean isListenerWithEventInvoked() {
		return listenerWithEventInvoked;
	}

}