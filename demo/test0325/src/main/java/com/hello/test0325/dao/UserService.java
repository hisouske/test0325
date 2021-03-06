package com.hello.test0325.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

import javax.transaction.Transactional;

import com.hello.test0325.dbtable.T200227item;
import com.hello.test0325.dbtable.T200227market;
import com.hello.test0325.dbtable.T200227member;


  @Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

//로그인 수정 전
	// public User getUsername(String username) {
	// 	return userRepository.findByUsername(username);
	// }
	//로그인 수정 후
	
	public T200227member login(String username,String password) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		T200227member user = userRepository.findByUsername(username);
		if(user == null)throw new UsernameNotFoundException(username + "is not found.");
		if(!passwordEncoder.matches(password, user.getPassword()))throw new UsernameNotFoundException(username + "is not.");
		return user;
	}

	public T200227member select(String username){
		T200227member user = userRepository.findByUsername(username);
		return user;
	}

//회원가입
	public void saveUsername(T200227member user){
	System.out.println(user);
T200227member a = userRepository.save(user);
System.out.println(a);
}
//마켓가입
	public void saveMarket(T200227market market){
	System.out.println(market);
	userRepository.save(market);
}
//상품등록
public void saveitem(T200227item item){
	System.out.println(item);
	userRepository.save(item);
}

//id 기준으로 마켓 찾기 
public List<Object> findMyMarket(String username){

	List<Object> mymarket = userRepository.findMarketByUsername(username);
	return mymarket;
}




	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDetails user = userRepository.findByUsername_(username);
		System.out.println("&&&&&&&&&&" + user +"&&&&&&&&&&&&&");
		//List<GrantedAuthority> authorities = new ArrayList<>();
		//authorities.add (new SimpleGrantedAuthority(user.getAuthorities()));
		//ArrayList<User> users = (ArrayList<User>) userRepository.findByUsername_(username);

        if (user==null) {
			System.out.println("사용자정보 없음");

            throw new UsernameNotFoundException(username + "is not found.");
        }
	//	return new User(user.getUsername(),user.getPassword(),authorities);
	return user;
	}



}