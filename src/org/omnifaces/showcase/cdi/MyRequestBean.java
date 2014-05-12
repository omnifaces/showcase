package org.omnifaces.showcase.cdi;

import static java.lang.System.nanoTime;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;

import org.omnifaces.cdi.Eager;

@Model
@Eager(requestURI = "/cdi/Eager")
public class MyRequestBean {

	private long initTime;
	
	@PostConstruct
	public void init() {
		initTime = nanoTime();
	}
	
	public long getElapsedTime() {
		return nanoTime() - initTime;
	}
	
}
