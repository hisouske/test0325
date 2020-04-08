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
@RequestMapping(value = "/", method = RequestMethod.POST)
public class LoginController {
	@Autowired
	UserRepository userrepository;

	@Autowired
	UserService userService;
	
	@GetMapping("login")
	public String viewLogin(Model model) {

		model.addAttribute("login_message", "로그인이 필요합니다.");
		return "login";
	}

	// @GetMapping("logout")
	// public String logout(Model model) {

	// 	model.addAttribute("login_message", "로그아웃 완료.");
	// 	return "redirect:/login";
	// }

	@PostMapping("joinok")
	public String join(HttpServletRequest request,Model model) {
		// MemberRole role = new MemberRole();
		model.addAttribute("login_message", "가입완료 ! 로그인해주세요");
		String username = request.getParameter("uid");
		String userpw = request.getParameter("upw");
		System.out.println(username);
		// User user ;
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String password = passwordEncoder.encode(userpw);

	    User user = new UserBuilder()
				.username(username)
				.password(password)
				// .publiccode(0)
				.memcourse("a+b+c")
				.emailadd("Y")
				.authority("ROLE_USER")
				.build();
				userService.saveUsername(user);
			

	System.out.println("------------");
	//userrepository.save(user);
	return "login";
}
}
 