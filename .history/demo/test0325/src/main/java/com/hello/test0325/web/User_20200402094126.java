package com.hello.test0325.web;

import java.util.Collection;
import java.util.List;

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

    private String username;
   
    private String password;
    private String Authority;
    

    // JPA 에 사용되는 instence
    public User() {
        
    }
    @Builder
    public User(String username,
    String password,String authority){
        // this.id = id;
        this.username = username;
        this.password = password;
        this.Authority = authority;

   
       
    }

    @Override
    public String toString(){
        //System.out.println(id);
        return String.format("User[id="+id+",username="+username+",password="+password+",authority="+Authority+"]");
    }


    @Override
    public boolean isAccountNonLocked(){
        System.out.println(">>>> isAccountNonLocked <<<<");
        return true;
    }
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
    System.out.println(">>>> getAuthorities <<<<");
    List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        
    authorities.add(new SimpleGrantedAuthority(person.getAuth()));
    
    return authorities;
        return null;
	}
	@Override
	public boolean isAccountNonExpired() {
        System.out.println(">>>> isAccountNonExpired <<<<");
		return false;
	}
	@Override
	public boolean isCredentialsNonExpired() {
        System.out.println(">>>> isCredentialNonExpired <<<<");
		return false;
	}
	@Override
	public boolean isEnabled() {
        System.out.println(">>>> isEnable <<<<");
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