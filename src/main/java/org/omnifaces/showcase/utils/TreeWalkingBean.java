package org.omnifaces.showcase.utils;

import static org.omnifaces.util.Components.forEachComponent;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIOutput;
import javax.inject.Named;

import org.omnifaces.util.Callback.WithArgument;

@Named
@RequestScoped
public class TreeWalkingBean {

	private UIComponent component;
	
	public int getPageComponentCount() {
		
		final int[] count = new int[1];
		
		forEachComponent()
			.invoke(new WithArgument<UIComponent>() { @Override public void invoke(UIComponent component) {
				count[0]++;
			}
		});
		
		return count[0];
	}
	
	public List<String> getAllIdsInGroup() {
		
		final List<String> textIds = new ArrayList<>();
		
		forEachComponent()
			.fromRoot(component)
			.invoke(new WithArgument<UIComponent>() { @Override public void invoke(UIComponent component) {
				textIds.add(component.getId());
			}
		});
		
		return textIds;
	}
	
	public List<String> getTextIdsInGroup() {
		
		final List<String> textIds = new ArrayList<>();
		
		forEachComponent()
			.fromRoot(component)
			.ofTypes(UIOutput.class)
			.invoke(new WithArgument<UIComponent>() { @Override public void invoke(UIComponent component) {
				textIds.add(component.getId());
			}
		});
		
		return textIds;
	}
	
	public UIComponent getComponent() {
		return component;
	}

	public void setComponent(UIComponent component) {
		this.component = component;
	}
	
}