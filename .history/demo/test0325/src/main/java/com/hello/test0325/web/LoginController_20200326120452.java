package com.hello.test0325.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

import com.hello.test0325.web.User.UserBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping(value = "/login", method = RequestMethod.POST)
public class LoginController {

	@GetMapping
	public String viewLogin(Model model) {

		model.addAttribute("login_message", "로그인이 필요합니다.");
		return "login";
	}

	@Autowired
	UserRepository userrepository;

	@PostMapping("/join")
	public String join(HttpServletRequest request) {
		// MemberRole role = new MemberRole();

		String username = request.getParameter("uid");
		String password = request.getParameter("upw");

		// User user ;
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		System.out.println(passwordEncoder.encode(password));

	
		User user = User.builder(username,password).build();
	//user.setPassword(passwordEncoder.encode(user.getPassword()));


	System.out.println("------------");
	//userrepository.save(user);
	return "login";
}
}
 