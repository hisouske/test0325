package com.hello.test0325.dao;

import com.hello.test0325.dbtable.T200227member;

import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.userdetails.UserDetails;


public interface UserRepository extends CrudRepository<T200227member,Long>{
   //List<User> findByUsername(String string);

   T200227member findByUsername(String username);
   UserDetails findByUsername_(String username);
  // List<User> findByPassword(String password);
  T200227member save(T200227member user);


}