package com.hello.test0325.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;


@RequestMapping("/login")
public class LoginController {
	
	@GetMapping
	public String viewLogin(Model model) {
		
		model.addAttribute("login_message", "로그인이 필요합니다.");
		return "login";
	}

@Autowired
MemberRepository memberRepository;

@PostMapping("")
public String create(Member member) {
	MemberRole role = new MemberRole();
	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	member.setUpw(passwordEncoder.encode(member.getUpw()));
	role.setRoleName("BASIC");
	member.setRoles(Arrays.asList(role));
	memberRepository.save(member);
	return "redirect:/";
}
}
 