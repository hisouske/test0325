package com.hello.test0325.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

import com.hello.test0325.dao.UserRepository;
import com.hello.test0325.dao.UserService;
import com.hello.test0325.dbtable.T200227member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping(value = "/", method = RequestMethod.POST)
public class MainController {
	@Autowired
	UserRepository userrepository;

	@Autowired
	UserService userService;

	@GetMapping("marketjoin")
	public String viewmem(Model model) {

		model.addAttribute("marketjoin_message", "본인아이디);
		return "marketjoin";
	}
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

	    T200227member t200227member = T200227member.builder()
				.username(username)
				.password(password)
				.publiccode(0)//현재 부모테이블에 데이터 있음
				.memcourse("a+b+c")
				.emailadd("Y")
				.authority("ROLE_USER")
				.build();
				userService.saveUsername(t200227member);
			

	System.out.println("------------");
	//userrepository.save(user);
	return "login";
}
}
 