package org.omnifaces.showcase.components;

import static org.omnifaces.util.Messages.addGlobalInfo;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class InputHiddenBean {

    private boolean toggled1;
    private boolean toggled2;

    public void toggle1() {
        this.toggled1 = !toggled1;
    }

    public void toggle2() {
        this.toggled2 = !toggled2;
    }

    public void submit() {
        addGlobalInfo("Submitted!");
    }

    public boolean isToggled1() {
		return toggled1;
	}

    public void setToggled1(boolean toggled1) {
		this.toggled1 = toggled1;
	}

    public boolean isToggled2() {
		return toggled2;
	}

    public void setToggled2(boolean toggled2) {
		this.toggled2 = toggled2;
	}
}
