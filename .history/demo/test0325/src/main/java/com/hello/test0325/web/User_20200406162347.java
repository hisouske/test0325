package com.hello.test0325.web;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Data
@Table(name="\"ROLE_ADMIN\"")
public class User implements UserDetails{
    private static final long serialVersionUID = 1L;

    @Id
    private String memid;
    private String mempw;
    private int publiccode;
    private String memcourse;
    private String emailadd;
    private String Authority;

    // JPA 에 사용되는 instence
    public User() {
        
    }
    
    @Builder
    public User(String memid,String mempw, int publiccode,
    String memcourse,String emailadd,String authority){
        // this.id = id;
        this.memid = memid;
        this.mempw = mempw;
        this.publiccode = publiccode;
        this.memcourse = memcourse;
        this.emailadd = emailadd;
        this.Authority = authority;

        System.out.println("builder"+this);
    }

    @Override
    public String toString(){
        return String.format("User[memid="+memid+",mempw="+mempw+",publiccode="+publiccode+",memcourse="+memcourse+",emailadd="+emailadd+",authority="+Authority+"]");
    }

    @Override
    public boolean isAccountNonLocked(){
        System.out.println(">>>> isAccountNonLocked <<<<");
        return true;
    }


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority(this.getAuthority()));
        System.out.println(">>>> getAuthorities <<<<"+ authorities);
    return authorities;
    }
    

	@Override
	public boolean isAccountNonExpired() {
        System.out.println(">>>> isAccountNonExpired <<<<");
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
        System.out.println(">>>> isCredentialNonExpired <<<<");
		return true;
	}
	@Override
	public boolean isEnabled() {
        System.out.println(">>>> isEnable <<<<");
		return true;
	}

    @Override
    public String getMemid() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getUsername() {
        // TODO Auto-generated method stub
        return null;
    }

}