package org.omnifaces.showcase.scripts;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import org.omnifaces.util.Messages;

@Named
@RequestScoped
public class FixViewStateBean {

	public void submit1() {
		Messages.addGlobalInfo("Form 1 is submitted");
	}

	public void submit2() {
		Messages.addGlobalInfo("Form 2 is submitted");
	}

}