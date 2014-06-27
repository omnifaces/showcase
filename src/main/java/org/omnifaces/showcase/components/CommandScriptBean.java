package org.omnifaces.showcase.components;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Faces;

@ManagedBean
@ViewScoped
public class CommandScriptBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<String> hashFragments;

	@PostConstruct
	public void init() {
		hashFragments = new ArrayList<>();
	}

	public void addHashFragment() {
		hashFragments.add(Faces.getRequestParameter("hashFragment"));
	}

	public List<String> getHashFragments() {
		return hashFragments;
	}

}