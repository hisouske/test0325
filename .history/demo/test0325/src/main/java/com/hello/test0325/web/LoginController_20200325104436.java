package com.yna.test200318.securingweb;

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
}
// 	@Controller
// 	@RequestMapping("/aaa")
// public class LoginController {
	
// 	@GetMapping
// 	public String viewLogin(Model model,HttpServletRequest request) {
// 		String password = request.getParameter("password");
// 		String username = request.getParameter("username");
// 		return "login";
// 	}

// }