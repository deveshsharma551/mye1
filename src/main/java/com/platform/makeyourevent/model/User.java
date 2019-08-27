package com.platform.makeyourevent.model;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection="user" )
public class User extends ParentModel{
	
	private String username;
	private String password;
	private List<String> roles;
	
	
	
	/*public User(User user) {
		super();
		this.username = user.getUsername();
		this.password = user.getPassword();
		this.roles = user.getRoles();
	}*/
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<String> getRoles() {
		return roles;
	}
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

}
