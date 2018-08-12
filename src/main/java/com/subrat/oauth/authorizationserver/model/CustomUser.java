package com.subrat.oauth.authorizationserver.model;

import org.springframework.security.core.userdetails.User;

public class CustomUser extends User {
 	private static final long serialVersionUID = 1891620323037074269L;

	public CustomUser(UserModel user) {
		super(user.getUsername(), user.getPassword(), user.getGrantedAuthorities()); 
	}

	
}
