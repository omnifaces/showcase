package org.omnifaces.showcase.push;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicLong;

import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.cdi.PushContext;
import org.omnifaces.cdi.ViewScoped;

@Named
@ViewScoped
public class PushBean implements Serializable {

	private static AtomicLong counter = new AtomicLong();

	private boolean enabled;

	@Inject
	private PushContext push;

	public void toggle() {
		enabled = !enabled;
	}

	public void increment() {
		long newvalue = counter.incrementAndGet();
		push.send("counter", newvalue);
	}

	public boolean isEnabled() {
		return enabled;
	}

	public Long getCount() {
		return counter.get();
	}

}