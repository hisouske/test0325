package com.hello.test0325.dbtable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name="\"200227member\"")
public class T200227member implements UserDetails{
    private static final long serialVersionUID = 1L;

    @Id
    @ㅎㄷ
    @Column(name = "memid",nullable = false)
    private String username;
    @Column(name = "mempw",nullable = false)
    private String password;
    private int publiccode;
    private String memcourse;
    private String emailadd;
    private String Authority;
    @ManyToOne
    @JoinColumn(name = "publiccode", insertable = false, updatable = false)
    private T200227public t200227public;

    // JPA 에 사용되는 instence
    public T200227member() {
        
    }
    @Builder
    public T200227member(String username,String password,int publiccode,String memcourse, String emailadd,String authority){
        // this.id = id;
        this.username = username;
        this.password = password;
        this.publiccode = publiccode;
        this.memcourse = memcourse;
        this.emailadd = emailadd;
        this.Authority = authority;

        System.out.println("builder >>>"+this);
    }

    @Override
    public String toString(){
       System.out.println("builder >>>"+String.format("User[username="+username+",password="+password+",memcourse="+memcourse+",emailadd="+emailadd+",authority="+Authority+"]"));

        return String.format("User[username="+username+",password="+password+",publiccode="+publiccode+",memcourse="+memcourse+",emailadd="+emailadd+",authority="+Authority+"]");
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

}