package org.omnifaces.showcase.cdi;

import static java.lang.System.nanoTime;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.inject.Model;

import org.omnifaces.cdi.Eager;

@Model
@Eager(requestURI = "/cdi/Eager")
public class MyEagerRequestBean {

	private long initTime;
	private Long elapsedTime;

	@PostConstruct
	public void init() {
		initTime = nanoTime();
	}

	public long getElapsedTime() {
		if (elapsedTime == null) {
			elapsedTime = nanoTime() - initTime;
		}

		return elapsedTime;
	}

}