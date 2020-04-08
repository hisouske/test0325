package com.hello.test0325.web;


import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.userdetails.UserDetails;


public interface UserRepository extends CrudRepository<User,String>{
   //List<User> findByUsername(String string);

   User findByUsername(String memid);
   UserDetails findByUsername_(String memid);
  // List<User> findByPassword(String password);
   User save(User user);


}