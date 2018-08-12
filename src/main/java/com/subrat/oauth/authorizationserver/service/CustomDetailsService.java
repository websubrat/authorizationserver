package com.subrat.oauth.authorizationserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.subrat.oauth.authorizationserver.dao.UserDAO;
import com.subrat.oauth.authorizationserver.model.CustomUser;
import com.subrat.oauth.authorizationserver.model.UserModel;

@Service
public class CustomDetailsService implements UserDetailsService {

	@Autowired
	UserDAO userDAO;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserModel userModel = userDAO.getUserDetail(username);
		CustomUser user = new CustomUser(userModel);
		return user;
	}
	

}
