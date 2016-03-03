package org.omnifaces.showcase.push;

import static org.omnifaces.util.Faces.refresh;
import static org.omnifaces.util.Faces.setSessionAttribute;
import static org.omnifaces.util.Messages.addGlobalError;
import static org.omnifaces.util.Messages.addGlobalInfo;

import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.Future;

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
		app.send(LocalDateTime.now().toString());
	}

	public void pushSess() {
		sess.send(LocalDateTime.now().toString());
	}

	public void pushView() {
		view.send(LocalDateTime.now().toString());
	}

	public void pushUser(String recipientUser) {
		Set<Future<Void>> sent = sess.send(LocalDateTime.now().toString(), recipientUser);

		if (sent.isEmpty()) {
			addGlobalError("This user does not exist!");
		}
		else {
			addGlobalInfo("Sent to {0} sockets", sent.size());
		}
	}

}