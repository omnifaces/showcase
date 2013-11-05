package org.omnifaces.showcase.scripts;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.omnifaces.util.Messages;

@ManagedBean
@RequestScoped
public class FixViewStateBean {

	public void submit1() {
		Messages.addGlobalInfo("Form 1 is submitted");
	}

	public void submit2() {
		Messages.addGlobalInfo("Form 2 is submitted");
	}

}