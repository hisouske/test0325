package com.hello.test0325.dao;

import java.util.List;

import com.hello.test0325.dbtable.T200227item;
import com.hello.test0325.dbtable.T200227market;
import com.hello.test0325.dbtable.T200227member;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;


public interface UserRepository extends CrudRepository<T200227member,Long>{
   //List<User> findByUsername(String string);

   T200227member findByUsername(String username);
   UserDetails findByUsername_(String username);
   
  // List<User> findByPassword(String password);
  @Query(value = "select*from 200227market m where m.memid=:username",nativeQuery = true )
  List<Object> findMarketByUsername(@Param("username")String username);
  
  T200227member save(T200227member user);

// @Query("insert into 200227member values(")
// void memsave(T200227member user);

  T200227market save(T200227market market);
  T200227item save(T200227item item);


}