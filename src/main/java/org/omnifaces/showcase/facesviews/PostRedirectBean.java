package org.omnifaces.showcase.facesviews;

import java.io.Serializable;
import java.util.Date;

import jakarta.enterprise.context.Conversation;
import jakarta.enterprise.context.ConversationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@ConversationScoped
public class PostRedirectBean implements Serializable {

	private static final long serialVersionUID = -1209535053351028792L;
	
	private Date date = new Date();

	@Inject
	private Conversation conversation;

	public String toViewsDemo() {
		
		if (conversation.isTransient()) {
			conversation.begin();
		}
		
		return "/viewsdemo?faces-redirect=true";
	}

	public String toSecond() {
		
		if (conversation.isTransient()) {
			conversation.begin();
		}

		return "/other/second?faces-redirect=true";
	}

	public Date getDate() {
		return date;
	}

}