
package com.hello.test0325.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
 
/**
 * 인증 프로바이더
 * 로그인시 사용자가 입력한 아이디와 비밀번호를 확인하고 해당 권한을 주는 클래스
 * 
 * 
 *
 */
@Component
public class AuthProvider implements AuthenticationProvider{
    
    @Autowired
    UserService userService;
     
    // @Autowired
    //  BCryptPasswordEncoder passwordEncoder;
    @Bean // 비밀번호 암호화 객체
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

    // @Autowired //생성자나 세터 등을 사용하여 의존성 주입을 하려고 할 때, 해당 빈을 찾아서 주입해주는 annotation
	// 	public void configure(AuthenticationManagerBuilder auth) throws Exception { //모든 인증 Manager
	// 		auth.eraseCredentials(false);
	// 		//auth.inMemoryAuthentication().withUser("zzang22yn").password("1125").roles("ROLE_ADMIN"); 
	// 	}

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = (String) authentication.getPrincipal();
        String webpassword= (String)authentication.getCredentials();
        //SecurityContextHolder.getContext().setAuthentication(authentication);  authentication 수동세팅 _ null값 방지 
       // System.out.println(authentication);
       
       
     //   UsernamePasswordAuthenticationToken a =null;
       User user = userService.getUsername(username);
        // user가 없거나 비밀번호가 맞지 않는 경우.
        if (user!=null&& webpassword!=null) {
            String dbpassword  = user.getPassword();
            // System.out.println(dbpassword +"/" + webpassword +"/");
            // System.out.println(passwordEncoder.matches(webpassword, dbpassword));
         if (!passwordEncoder.matches(webpassword, dbpassword)) {
           System.out.println("-------this ------");
           throw new UsernameNotFoundException(username + "is not found.");
         }
    }else{
        throw new UsernameNotFoundException(username + "is not found.");
    }

    List<GrantedAuthority> grantedAuthorityList = new ArrayList<GrantedAuthority>();
    grantedAuthorityList.add(new SimpleGrantedAuthority("ROLE_USER"));
   // userService.setCurrentUser(user);
            System.out.println(userService.getCurrentUser());
          System.out.println(authentication);
   return new MyAuthentication(username,webpassword,grantedAuthorityList,user);
    }

@Override
public boolean supports(Class<?> authentication) {
    System.out.println("xxxxxxxxxsupportsxxxxxxxxxxxx"+authentication);
return authentication.equals(UsernamePasswordAuthenticationToken.class);
//return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
}

}

