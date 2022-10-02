package org.omnifaces.showcase.demo;

import java.io.IOException;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

@Named
@RequestScoped
public class Auth {

	private String username;
	private String password;

	public void login() throws IOException {
		if ("password".equals(password)) {
			Faces.invalidateSession(); // "good practice", minimizes risk in session fixation hack.
			Faces.setSessionAttribute("user", username);
			Faces.refresh();
		}
		else {
			Messages.addGlobalError("Unknown login, please try again");
		}
	}

	public void logout() throws IOException {
		Faces.invalidateSession();
		Faces.refresh();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}