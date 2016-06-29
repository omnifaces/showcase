package org.omnifaces.showcase.cdi;

import java.io.IOException;
import java.util.UUID;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.cdi.Cookie;
import org.omnifaces.util.Faces;

@Named
@RequestScoped
public class CdiCookieBean {

	@Inject @Cookie
	private String testCookie;

	public void add() throws IOException {
		Faces.addResponseCookie("testCookie", UUID.randomUUID().toString(), -1);
		Faces.refresh();
	}

	public String getTestCookie() {
		return testCookie;
	}

}