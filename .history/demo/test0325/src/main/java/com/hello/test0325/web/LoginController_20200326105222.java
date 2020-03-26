package com.hello.test0325.web;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@RequestMapping("/login")
public class LoginController {
	
	@PostMapping
	public String viewLogin(Model model) {
		
		model.addAttribute("login_message", "로그인이 필요합니다.");
		return "login";
	}

// @Autowired
// UserRepository userrepository ;
// @PostMapping("/join")
// public String join(User user) {
// 	//MemberRole role = new MemberRole();
// 	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
// 	user.setPassword(passwordEncoder.encode(user.getPassword()));


// 	System.out.println("------------");
// 	//userrepository.save(user);
// 	return "login";
// }
}
 