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
    // private Long id;
    @Column(name="username")
    private String username;
    @Column(name="password")
    private String password;
    

    // JPA 에 사용되는 instence
    public User() {
        
    }
    @Builder
    public User(String userid,String password){
        // this.id = id;
        this.username = username;
        this.password = password;
       
    }

    @Override
    public String toString(){
        //System.out.println(id);
        return String.format("User[username="+username+",password="+password+"]");
    }

    public User toEntity() {
      
        return User.builder()
        // .id(id)
        .username(username)
        .password(password)
        .build();
    }
}