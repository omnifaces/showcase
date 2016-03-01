package org.omnifaces.showcase.push;

import static org.omnifaces.util.Faces.refresh;
import static org.omnifaces.util.Faces.setSessionAttribute;

import java.io.IOException;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.UUID;

import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.cdi.Push;
import org.omnifaces.cdi.PushContext;
import org.omnifaces.cdi.ViewScoped;

@Named
@ViewScoped
public class PushTestBean implements Serializable {

	@Inject @Push
	private PushContext app;

	@Inject @Push
	private PushContext sess;

	@Inject @Push
	private PushContext view;

	public void open() throws IOException {
		setSessionAttribute("pushTestUser", UUID.randomUUID().toString());
		refresh();
	}

	public void pushApp() {
		app.send(ZonedDateTime.now().toString());
	}

	public void pushSess() {
		sess.send(ZonedDateTime.now().toString());
	}

	public void pushView() {
		view.send(ZonedDateTime.now().toString());
	}

	public void pushUser(String recipientUser) {
		sess.send(ZonedDateTime.now().toString(), recipientUser);
	}

}