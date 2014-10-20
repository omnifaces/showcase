package org.omnifaces.showcase.components;

import static java.util.Arrays.asList;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;
import org.omnifaces.showcase.model.User;

@Named
@ViewScoped
public class MyTableBacking implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private List<User> users;
	private List<User> filteredUsers;

	@PostConstruct
	public void init() {
		users = asList(
			new User("John",  "Peterson", 32),
			new User("Linda", "Harrison", 33),
			new User("Akira", "Yamada",   18),
			new User("Fangni", "Chen",    39)					
		);				
	}
	
	public List<User> getUsers() {
		return users;
	}
	
	public List<User> getFilteredUsers() {
		return filteredUsers;
	}

	public void setFilteredUsers(List<User> filteredUsers) {
		this.filteredUsers = filteredUsers;
	}
	
}