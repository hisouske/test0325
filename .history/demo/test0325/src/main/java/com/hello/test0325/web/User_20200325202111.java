package com.hello.test0325.web;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="user")
public class User implements Serializable{
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(name="username")
    private String username;
    @Column(name="password")
    private String password;
    @Column(name="enabled")
     private String enabled;
    @Column(name="authority")
    private String authority;

    // JPA 에 사용되는 instence
    public User() {
        
    }
    @Builder
    public User(Long id, String username, String password,String enabled,String authority){
        this.id = id;
        this.username = username;
        this.password = password;
        this.authority=authority;
        this.enabled=enabled;
    }

    @Override
    public String toString(){
        //System.out.println(id);
        return String.format("User[id="+id+",username="+username+",password="+password+",enabled="+enabled+",authority="+authority+"]");
    }

    public User toEntity() {
      
        return User.builder()
        .id(id)
        .username(username)
        .password(password)
        .authority(authority)
        .enabled(enabled)
        .build();
    }
}