package org.omnifaces.showcase.push;

import static org.omnifaces.cdi.Push.Type.SSE;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.inject.Inject;
import jakarta.inject.Named;

import org.omnifaces.cdi.Push;
import org.omnifaces.cdi.PushContext;
import org.omnifaces.cdi.ViewScoped;

/**
 * Backing bean for the {@code <o:sse>} showcase demo.
 * Demonstrates sending push messages via Server-Sent Events.
 */
@Named
@ViewScoped
public class SseBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject @Push(type = SSE, channel = "sseDemo")
	private PushContext sseDemo;

	/**
	 * Sends the current server timestamp to all connected SSE clients.
	 */
	public void send() {
		sseDemo.send(LocalDateTime.now().toString());
	}

}
