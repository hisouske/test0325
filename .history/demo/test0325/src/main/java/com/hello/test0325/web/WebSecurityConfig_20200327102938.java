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

@Configuration
@EnableWebSecurity //Spring Security 설정한 클래스
public class WebSecurityConfig extends WebSecurityConfigurerAdapter /** WebSecurityConfig instence 생성 하기 위한 클래스 **/{
	@Autowired
    AuthProvider authProvider;

	private UserService userservice;

	@Bean // 비밀번호 암호화 객체
	public PasswordEncoder passwordEnoder(){
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http)/** HTTP 요청에 대한 웹 기반 보안 구성 */ throws Exception {
		http
			.authorizeRequests() //페이지 접근 권한 설정
				.antMatchers("/", "/home","/join","/login/*").permitAll() // home 경로 권한없이 접근 가능
				.anyRequest().authenticated() //인증된 사용자만 접근 가능 하도록 설정
				.and()
			.formLogin() //로그인 설정, httpSession 기본적 이용
				.loginPage("/login") //커스텀 로그인 폼, action 경로랑 일치 해야함
				.usernameParameter("username")
				.passwordParameter("password")
				.failureUrl("/login?error") //로그인 실패시 이동 하는 페이지 설정
				.defaultSuccessUrl("/home",true) // 로그인 성공시 이동 하는 페이지 설정
				.permitAll() //로그인 페이지 모든 권한 설정
				.and()
			.logout() //로그아웃 설정
				.permitAll() //update 예정
				// 로그인 프로세스가 진행될 provider
				.and()
				//.authenticationProvider(authProvider);
				

			System.out.println("++++++//////"+http.formLogin());
		}

	

	// @Bean
	// @Override
	// public UserDetailsService userDetailsService() {
	// 	UserDetails user =
	// 		User.withDefaultPasswordEncoder()
	// 			.username("user")
	// 			.password("password")
	// 			.roles("USER")
	// 			.build();
	// 			System.out.println("websecurityconfig : userpassword : "+user.getPassword());
	// 	return new InMemoryUserDetailsManager(user);
	// }

//@Autowired 
public void configAuthentication(AuthenticationManagerBuilder auth, DataSource dataSource) throws Exception { //모든 인증 Manager
	System.out.println("-----ddd------");
	System.out.println(auth);
	System.out.println("DATASOURCE"+dataSource);
	auth.userDetailsService(userservice).passwordEncoder(passwordEnoder());

//	auth.jdbcAuthentication().dataSource(dataSource) .usersByUsernameQuery("select username, password, enabled from user where username = ?") .authoritiesByUsernameQuery("select username, authority as role from user where username = ?") .passwordEncoder(new BCryptPasswordEncoder()); /* 패스워드 암호화 시 사용 */ 
}
}

