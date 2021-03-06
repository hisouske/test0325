package com.hello.test0325.controller;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import com.hello.test0325.dao.UserRepository;
import com.hello.test0325.dao.UserService;
import com.hello.test0325.dbtable.T200227item;
import com.hello.test0325.dbtable.T200227market;
import com.hello.test0325.dbtable.T200227member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
@Repository
@Controller
@Transactional
@RequestMapping(value = "/", method = RequestMethod.POST)
public class MainController{
	
	@Autowired
	UserRepository userrepository;

	@Autowired
	UserService userService;
	
	 @PersistenceUnit
	 private EntityManagerFactory emf= Persistence.createEntityManagerFactory("jpabook");
	 @PersistenceContext
	 private EntityManager em;


	//static EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
    // EntityManagerFactory emf = Persistence.createEntityManagerFactory("----");

	// EntityManager em = emf.createEntityManager();
	// EntityTransaction tx = em.getTransaction();


	@GetMapping("itemadd")
	public String addformview(Model model,HttpServletRequest request) {

		model.addAttribute("marketjoin_message", "controller item message");
		return "itemadd";
	}


	@GetMapping("marketjoin")
	public String viewmem(Model model,HttpServletRequest request) {
		
		Cookie[] getCookie = ((HttpServletRequest) request).getCookies();
		if(getCookie != null){
		for(int i=0; i<getCookie.length; i++){
		Cookie c = getCookie[i];
		if(c.getName().equals("memid")){
		String cookieid = c.getValue();
		//entityManager에 저장된 member 사용
		List<Object> mymarket = userService.findMyMarket(cookieid);
	System.out.println("findmymarket>>"+mymarket);
	}
	}}
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
	
		model.addAttribute("login_message", "가입완료 ! 로그인해주세요");
		String username = request.getParameter("uid");
		String userpw = request.getParameter("upw");
		System.out.println("joinok >>"+username);
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
				System.out.println(t200227member);
				//entitymanager 사용 > @Transactional 으로 메소드 종료시 자동 db추가 
				em.persist(t200227member);
				//userService.saveUsername(t200227member);
		

	System.out.println("------------");
	return "login";
}

@PostMapping("marketjoinok")
	public String marketjoin(HttpServletRequest request,Model model,EntityManager em) {
		T200227member member =null;
		//쿠키에 저장된 id로 재 조회
		model.addAttribute("login_message", "가입완료 ! 로그인해주세요");
		Cookie[] getCookie = ((HttpServletRequest) request).getCookies();
		if(getCookie != null){
		for(int i=0; i<getCookie.length; i++){
		Cookie c = getCookie[i];
		if(c.getName().equals("memid")){
		String cookieid = c.getValue();
		
		//member = em.find(T200227member.class,cookieid);
		System.out.println("5 >>"+member);
		if(member!=null)member = userService.select(cookieid);
	} 
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
member.getT200227markets().add(t200227market);
t200227market.setJoin200227member(member);

	System.out.println("------------");
	//userrepository.save(user);
	return "login";
}
@PostMapping("itemaddok")
	public String itemadd(HttpServletRequest request,Model model) {
		

		// String iid = request.getParameter("iid");
		// System.out.println(iid);
		// T200227item t200227item = T200227item.builder()
		// .unioncode(0)
		// .price(0)
		// .itemname("aaa")
		// .itempic("itempic")
		// .itemtext("itemtext")
		// .market(market)
		// .build();
	//userrepository.save(user);
	return "login";
}
}
 