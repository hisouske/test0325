package com.hello.test0325.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import com.hello.test0325.dao.UserRepository;
import com.hello.test0325.dao.UserService;
import com.hello.test0325.dbtable.T200227market;
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
	public String viewmem(Model model,HttpServletRequest request) {
		Cookie[] getCookie =  request.getCookies();

		if(getCookie != null){
		
		for(int i=0; i<getCookie.length; i++){
		Cookie c = getCookie[i];
		String name = c.getName(); // 쿠키 이름 가져오기
		String value = c.getValue(); // 쿠키 값 가져오기
		System.out.println(name+value);
		}
		}
		model.addAttribute("marketjoin_message", "controller market message");
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

@PostMapping("marketjoinok")
	public String marketjoin(HttpServletRequest request,Model model) {
		T200227member member =null;
		model.addAttribute("login_message", "가입완료 ! 로그인해주세요");
		Cookie[] getCookie = ((HttpServletRequest) request).getCookies();
		if(getCookie != null){
		for(int i=0; i<getCookie.length; i++){
		Cookie c = getCookie[i];
		if(c.getName().equals("memid")){// 쿠키 이름 가져오기
		String cookieid = c.getValue();
		member = userService.select(cookieid);
	} // 쿠키 값 가져오기
	
		}
		}



		String marketname = request.getParameter("mid");
		String marketpic = request.getParameter("mpic");
		String marketintro = request.getParameter("mintro");
	  
		System.out.println(marketname+marketpic+marketintro);

		T200227market t200227market = T200227market.builder()
			.marketcode(0)
			.marketname(marketname)
			.marketpic(marketpic)
			.markettext(marketintro)
			.member(member)
			.build();
			userService.saveMarket(t200227market);
			

	System.out.println("------------");
	//userrepository.save(user);
	return "login";
}
}
 