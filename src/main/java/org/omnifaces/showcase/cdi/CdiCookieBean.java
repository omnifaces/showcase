package org.omnifaces.showcase.cdi;

import java.util.UUID;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import org.omnifaces.cdi.Cookie;
import org.omnifaces.util.Faces;

@Named
@RequestScoped
public class CdiCookieBean {

	@Inject @Cookie
	private String testCookie;

	public void add() {
		Faces.addResponseCookie("testCookie", UUID.randomUUID().toString(), -1);
		Faces.refresh();
	}

	public String getTestCookie() {
		return testCookie;
	}

}