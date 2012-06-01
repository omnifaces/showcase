package org.omnifaces.showcase.facesviews;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;

@RequestScoped
@ManagedBean
public class PostRedirectBean {

	public String toViewsDemo() {
		return "/viewsdemo?faces-redirect=true";
	}

	public String toSecond() {
		return "/other/second?faces-redirect=true";
	}

}