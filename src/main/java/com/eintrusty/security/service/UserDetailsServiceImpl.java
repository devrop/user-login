package com.eintrusty.security.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.eintrusty.utility.StringUtil;





@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Value("${eintrusty.app.regex1}")
    private String regex1;
 
	@Value("${eintrusty.app.regex2}")
    private String regex2;

	
	@Override
	public UserDetails loadUserByUsername(String usernameAndRole) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		System.out.println("regex 1" + regex1 + " regex2" + regex2);
		Map<String,Object> data = StringUtil.extractUserNameAndRoleFromBodyToken(usernameAndRole, this.regex1,this.regex2);
		String username = (String) data.get("username");
		List<String> roles = (List<String>) data.get("roles");
		System.out.println("Role" + roles);
		
		return UserPrinciple.build(username,roles);
		
		
	}

}
