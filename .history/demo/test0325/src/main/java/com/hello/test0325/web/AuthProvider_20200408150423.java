
package com.hello.test0325.web;

import java.util.ArrayList;
import java.util.List;

import com.hello.test0325.dao.UserService;
import com.hello.test0325.dbtable.T200227member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
 

/**
 * 인증 프로바이더
 * 로그인시 사용자가 입력한 아이디와 비밀번호를 확인하고 해당 권한을 주는 클래스
 */
@Component
public class AuthProvider implements AuthenticationProvider{
    
    @Autowired
    UserService userService;

    // @Bean // 비밀번호 암호화 객체
	// public PasswordEncoder passwordEncoder(){
	// 	return new BCryptPasswordEncoder();
	// }

    // @Autowired //생성자나 세터 등을 사용하여 의존성 주입을 하려고 할 때, 해당 빈을 찾아서 주입해주는 annotation
	// 	public void configure(AuthenticationManagerBuilder auth) throws Exception { //모든 인증 Manager
	// 		auth.eraseCredentials(false);
	// 	}

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = (String) authentication.getPrincipal();
        String webpassword= (String)authentication.getCredentials();
    
        T200227member user = userService.login(username,webpassword);
        System.out.println(user);

        List<GrantedAuthority> grantedAuthorityList = new ArrayList<GrantedAuthority>();
        grantedAuthorityList.add(new SimpleGrantedAuthority(user.getAuthority()));

        System.out.println(new UsernamePasswordAuthenticationToken(username, webpassword,grantedAuthorityList));
  return new UsernamePasswordAuthenticationToken(username, webpassword,grantedAuthorityList);
}

@Override
public boolean supports(Class<?> authentication) {
    System.out.println("xxxxxxxxxsupportsxxxxxxxxxxxx"+authentication);
//return authentication.equals(UsernamePasswordAuthenticationToken.class);
return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
}

}

