package com.hello.test0325.web;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Component;

@Configuration  //어노테이션기반 환경구성. 클래스가 하나 이상의 @Bean 메소드를 제공하고 스프링 컨테이너가 Bean정의를 생성하고 런타임시 그 Bean들이 요청들을 처리할 것을 선언
@EnableWebSecurity //Spring Security 설정한 클래스
public class WebSecurityConfig extends WebSecurityConfigurerAdapter /** WebSecurityConfig instence 생성 하기 위한 클래스 **/{
	
	@Autowired
	AuthProvider authProvider;
	
	// @Autowired
	// MyAuthenticationSuccessHandler successHandler;
	
	// @Bean // userservice 객체 동작은 하나 return null
	// public UserService userservice(){
	// 	return new UserService();
	// } autowired는 선언만 bean 은 객체리턴

	@Override
	public void configure(HttpSecurity http) throws Exception {/** HTTP 요청에 대한 웹 기반 보안 구성 */
			http.authorizeRequests() //페이지 접근 권한 설정.사용자 인증이 된 요청에 대해서만 요청을 허용한다.
				.antMatchers("/", "/home","/join","/login/*")
				.permitAll() // home 경로 권한없이 접근 가능
				.antMatchers("/admin").hasAuthority("ROLE_ADMIN")
				.anyRequest()
				.authenticated(); //인증된 사용자만 접근 가능 하도록 설정

			//	http.csrf().disable();
			http.formLogin() //로그인 설정, httpSession 기본적 이용. 사용자는 폼기반 로그인으로 인증 할 수 있습니다.
				.loginPage("/login") //커스텀 로그인 폼, action 경로랑 일치 해야함
				.failureUrl("/login?error") //로그인 실패시 이동 하는 페이지 설정
				.defaultSuccessUrl("/hello",true) // 로그인 성공시 이동 하는 페이지 설정
			//	.successHandler(successHandler)
				.permitAll(); //로그인 페이지 모든 권한 설정
			
			http.logout().permitAll(); //logout update 예정
				//로그인 프로세스가 진행될 provider
				
				//.httpBasic()//사용자는 HTTP기반 인증으로 인증 할 수 있습니다.
			http.authenticationProvider(authProvider);
				

			// System.out.println("++++++//////"+http.formLogin());
			//  System.out.println("configure messege : "+authProvider);
			// System.out.println(http);
		}
		// @Autowired
		// private UserService userservice;
	
	
		// @Autowired //생성자나 세터 등을 사용하여 의존성 주입을 하려고 할 때, 해당 빈을 찾아서 주입해주는 annotation
		// public void configure(AuthenticationManagerBuilder auth) throws Exception { //모든 인증 Manager
		// 	auth.userDetailsService(userservice).passwordEncoder(passwordEncoder());
		// 	auth.eraseCredentials(false);
		// 	//auth.inMemoryAuthentication().withUser("zzang22yn").password("1125").roles("ROLE_ADMIN"); 

		// }
		@Bean // 비밀번호 암호화 객체
		public PasswordEncoder passwordEncoder(){
			return new BCryptPasswordEncoder();
		}
	

	//회원가입시 필요 
}

