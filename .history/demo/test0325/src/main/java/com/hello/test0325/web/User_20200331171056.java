package com.hello.test0325.web;

import java.util.Collection;

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
public class User {

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




}