package com.eintrusty.security.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.eintrusty.dto.UserLoginDto;

public class UserPrinciple implements UserDetails {
	
	 private String username;
	 private Collection<? extends GrantedAuthority> authorities;
	

	  public UserPrinciple(String username,
				    		Collection<? extends GrantedAuthority> authorities) {

	        this.username = username;
	        this.authorities = authorities;
	    }
	
	
	public static UserPrinciple build(UserLoginDto user) {
        List<GrantedAuthority> authorities = user.getRoles().stream().map(
        		role ->
                new SimpleGrantedAuthority(role)
        ).collect(Collectors.toList());
 
        return new UserPrinciple(
                user.getUsername(),
                authorities
        );
    }
	
	public static UserPrinciple build(String username, List<String> roles) {
		 //List<GrantedAuthority> authorities = roles.stream().map(
	       // 		role ->
	        //        new SimpleGrantedAuthority(role)
	        //).collect(Collectors.toList());
	 
		 List<GrantedAuthority> authorities = new ArrayList<>();
		 for (String role : roles) {
			 authorities.add(new SimpleGrantedAuthority(role));
			
		}

	        return new UserPrinciple(
	                username,
	                authorities
	        );
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
