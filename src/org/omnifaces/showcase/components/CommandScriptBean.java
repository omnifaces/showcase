package org.omnifaces.showcase.components;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class CommandScriptBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String hashFragment;
	private List<String> hashFragments;

	@PostConstruct
	public void init() {
		hashFragments = new ArrayList<String>();
	}

	public void addHashFragment() {
		hashFragments.add(hashFragment);
	}

	public String getHashFragment() {
		return hashFragment;
	}

	public void setHashFragment(String hashFragment) {
		this.hashFragment = hashFragment;
	}

	public List<String> getHashFragments() {
		return hashFragments;
	}

}