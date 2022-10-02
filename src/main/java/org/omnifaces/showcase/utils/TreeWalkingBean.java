package org.omnifaces.showcase.utils;

import static org.omnifaces.util.Components.forEachComponent;

import java.util.ArrayList;
import java.util.List;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.component.UIComponent;
import jakarta.faces.component.UIOutput;
import jakarta.inject.Named;

@Named
@RequestScoped
public class TreeWalkingBean {

	private UIComponent component;

	public int getPageComponentCount() {

		final int[] count = new int[1];

		forEachComponent().invoke(component -> count[0]++);

		return count[0];
	}

	public List<String> getAllIdsInGroup() {

		final List<String> textIds = new ArrayList<>();

		forEachComponent()
			.fromRoot(component)
			.invoke(component -> textIds.add(component.getId()));

		return textIds;
	}

	public List<String> getTextIdsInGroup() {

		final List<String> textIds = new ArrayList<>();

		forEachComponent()
			.fromRoot(component)
			.ofTypes(UIOutput.class)
			.invoke(component -> textIds.add(component.getId()));

		return textIds;
	}

	public UIComponent getComponent() {
		return component;
	}

	public void setComponent(UIComponent component) {
		this.component = component;
	}

}