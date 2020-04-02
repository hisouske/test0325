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

public UserDetail(User user){

this.user = user;
}


@Override
public Collection<? extends GrantedAuthority> getAuthorities() {
    List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
    
    authorities.add(new SimpleGrantedAuthority(user.getAuthority()));
    return authorities;
}



    @Override
    public String getPassword() {
        return this.user.getPassword();

     
    }

    @Override
    public String getUsername() {
        // TODO Auto-generated method stub
        return this.user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return false;
    }

}