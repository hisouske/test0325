package com.hello.test0325.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
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
		UserDetails user = userRepository.findByUsername_(username);
		System.out.println("&&&&&&&&&&" + user +"&&&&&&&&&&&&&");
		//List<GrantedAuthority> authorities =  buildUserAuthority(user.getUsername());

        if (user == null) {
			System.out.println("&&&&&&&&&&" + user +"&&&&&&&&&&&&&");

            throw new UsernameNotFoundException(username + "is not found.");
        }
		return user;
	
	}



}