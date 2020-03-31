package com.hello.test0325.web;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="\"user\"")
public class User implements UserDetails{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(name="username")
    private String username;
    @Column(name="password")
    private String password;
    private Set<GrantedAuthority> Authorities;

    // JPA 에 사용되는 instence
    public User() {
        
    }
    @Builder
    public User(String username,String password){
        // this.id = id;
        this.username = username;
        this.password = password;


    }

    @Override
    public String toString(){
        //System.out.println(id);
        return String.format("User[id="+id+",username="+username+",password="+password+"]");
    }


    @Override
    public boolean isAccountNonLocked(){
        return true;
    }
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean isAccountNonExpired() {
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

    // public User toEntity() {
      
    //     return User.builder()
    //     // .id(id)
    //     .username(username)
    //     .password(password)
    //     .build();
    // }
}