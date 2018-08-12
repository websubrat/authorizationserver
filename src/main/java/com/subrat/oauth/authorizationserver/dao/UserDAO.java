package com.subrat.oauth.authorizationserver.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Repository;

import com.subrat.oauth.authorizationserver.model.UserModel;

@Repository
public class UserDAO {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public UserModel getUserDetail(String userName)
	{
		Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
		String sqlQuery = "SELECT PASSWORD from USERS where USERNAME=?";
		
		List<UserModel> list = jdbcTemplate.query(sqlQuery, new String[] {userName}, (ResultSet rs, int rowNum)->{
			
			UserModel user = new UserModel();
			user.setUsername(userName);
			user.setPassword(rs.getString("PASSWORD"));
			return user;
		});
		if(list !=null && list.size()>0)
		{
			GrantedAuthority ga = new SimpleGrantedAuthority("ROLE_SYSTEMADMIN");
			grantedAuthorities.add(ga);
			list.get(0).setGrantedAuthorities(grantedAuthorities);
			return list.get(0);
		}
		return null;
	}
	
	

}
