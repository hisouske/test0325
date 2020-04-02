package com.hello.test0325.web;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDetail implements UserDetails {
    /**
    	 *
    	 */
    private static final long serialVersionUID = 1L;
    private User user;

public UserDetail(UserDetails user2){
this.user = user2;
}


@Override
public Collection<? extends GrantedAuthority> getAuthorities() {
    List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
    authorities.add(new SimpleGrantedAuthority(user.getAuthority()));
    return authorities;
}


    @Override
    public String getPassword() {
        System.out.println(">>>getPassword>>>>");
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
        System.out.println(">>>getUsername>>>>");

        return this.user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        System.out.println(">>>isAccountExpired>>>>");
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