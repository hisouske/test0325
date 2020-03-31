package com.hello.test0325.web;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class CustomAuthenticationSuccess implements AuthenticationSuccessHandler {

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth)
            throws IOException, ServletException {
        System.out.println("Entered Authentication Successful Method");
        boolean isUser = false;
        Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
        String targetUrl = null;
        for(GrantedAuthority currentAuth : authorities){
            if(currentAuth.getAuthority().equalsIgnoreCase("ROLE_USER")){
                isUser = true;
                break;
            }
            else {
                throw new IllegalStateException();
            }
        }

        if(isUser){
            targetUrl = "/hello";
        }
        System.out.println("Entered Authentication Successful Method");
        redirectStrategy.sendRedirect(request, response, targetUrl);
    }
}