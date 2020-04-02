package com.hello.test0325.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hello.test0325.web.User.UserBuilder;



@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;


	public User getUsername(String username) {
		return userRepository.findByUsername(username);
	}

	public User login(String username,String password) {
		System.out.println(username+password);
		User user = userRepository.findByUsername(username);
		if(user == null)throw new UsernameNotFoundException(username + "is not found.");
		if(user.getPassword().equals(password)==false)throw new UsernameNotFoundException(username + "is not.");
		return user;
	}



	// @Transactional //비밀번호 암호화 > 가입시
	// public Long joinUser(User)

	public void saveUsername(User user){
	userRepository.save(user);
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