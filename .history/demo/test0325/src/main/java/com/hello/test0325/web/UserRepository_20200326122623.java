package com.hello.test0325.web;

import java.util.List;

import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<User,Long>{
   //List<User> findByUsername(String string);

   List<User> findByUsername(String username);
  // List<User> findByPassword(String password);
    void saveUser(User user);
}