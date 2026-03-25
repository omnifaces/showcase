package org.omnifaces.showcase.push;

import static org.omnifaces.cdi.Push.Type.NOTIFICATION;
import static org.omnifaces.cdi.push.Notification.createNotificationMessage;

import java.io.Serializable;

import jakarta.inject.Inject;
import jakarta.inject.Named;

import org.omnifaces.cdi.Push;
import org.omnifaces.cdi.PushContext;
import org.omnifaces.cdi.ViewScoped;

/**
 * Backing bean for the {@code <o:notification>} showcase demo.
 * Demonstrates sending browser notifications via SSE-based push.
 */
@Named
@ViewScoped
public class NotificationBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject @Push(type = NOTIFICATION, channel = "notificationDemo")
	private PushContext notificationDemo;

	/**
	 * Sends a basic browser notification with title and body.
	 */
	public void sendBasic() {
		notificationDemo.send(createNotificationMessage("Hello from OmniFaces!", "This notification was pushed from the server via SSE."));
	}

	/**
	 * Sends a browser notification with a URL. Clicking it navigates to the showcase homepage.
	 */
	public void sendWithUrl() {
		notificationDemo.send(createNotificationMessage("Visit OmniFaces", "Click to open the OmniFaces Showcase homepage.", "/"));
	}

}
