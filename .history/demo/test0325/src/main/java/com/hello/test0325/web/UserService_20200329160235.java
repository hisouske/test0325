package com.hello.test0325.web;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	public List<User> getUsername(String username) {
		return userRepository.findByUsername(username);
	}
	// @Transactional //비밀번호 암호화 > 가입시
	// public Long joinUser(User)

public void saveUsername(User user){
userRepository.save(user);
}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	System.out.println("*****************");
		return null;
	}

}