package org.omnifaces.showcase.components;

import java.util.Random;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.omnifaces.util.Messages;

@ManagedBean
@RequestScoped
public class MessagesBean {

	public void showHtmlMessage() {
		Messages.addGlobalWarn("Beware of potential"
			+ " <span style=\"background: yellow; border: 10px solid black; font-size: 1.5em;\">XSS attack holes</span>"
			+ " when <span style=\"color: red; font-weight: bold;\">user-controlled</span> input is redisplayed through"
			+ " messages! See also <a href=\"http://en.wikipedia.org/wiki/Cross-site_scripting\">Wikipedia</a>.");
	}

	public void showRandomMessages(int amount) {
		Random random = new Random(System.nanoTime());

		for (int i = 0; i < amount; i++) {
			Severity severity = (Severity) FacesMessage.VALUES.get(random.nextInt(4));
			String severityName = severity.toString().split("\\s")[0];
			Messages.addGlobal(severity, "This is a global {0} message", severityName);
		}
	}

}