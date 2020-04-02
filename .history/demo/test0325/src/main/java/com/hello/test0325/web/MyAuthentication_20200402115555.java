package com.hello.test0325.web;

import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import lombok.Data;


@Data
public class MyAuthentication extends UsernamePasswordAuthenticationToken{

	/**
	 *
	 */
    private static final long serialVersionUID = 1L;
    
    User user;


    public MyAuthentication(String id, String password, List<GrantedAuthority> GrantedAuthorityList, User user){
    }
    
    }