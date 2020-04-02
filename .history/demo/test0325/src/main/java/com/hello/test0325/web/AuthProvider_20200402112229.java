
package com.hello.test0325.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
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

    @Bean // 비밀번호 암호화 객체
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

    @Autowired //생성자나 세터 등을 사용하여 의존성 주입을 하려고 할 때, 해당 빈을 찾아서 주입해주는 annotation
		public void configure(AuthenticationManagerBuilder auth) throws Exception { //모든 인증 Manager
			auth.eraseCredentials(false);
			//auth.inMemoryAuthentication().withUser("zzang22yn").password("1125").roles("ROLE_ADMIN"); 

		}

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = (String) authentication.getPrincipal();
        String webpassword= (String)authentication.getCredentials();
        //SecurityContextHolder.getContext().setAuthentication(authentication);  authentication 수동세팅 _ null값 방지 
       // System.out.println(authentication);
       
       
     //   UsernamePasswordAuthenticationToken a =null;
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        List<User> user = userService.getUsername(username);
        System.out.println(user.size());
        // user가 없거나 비밀번호가 맞지 않는 경우.
        if (user.size()>0 && webpassword!=null) {
            String dbpassword  = user.get(0).getPassword();
            // System.out.println(dbpassword +"/" + webpassword +"/");
            // System.out.println(passwordEncoder.matches(webpassword, dbpassword));
         if (!passwordEncoder.matches(webpassword, dbpassword)) {
           System.out.println("-------this ------");
           throw new UsernameNotFoundException(username + "is not found.");
         }
    }else{
        throw new UsernameNotFoundException(username + "is not found.");

    }



    
System.out.println(authentication);
//return new UsernamePasswordAuthenticationToken(username, webpassword);

        //List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
   return authentication;
   // 로그인한 계정에게 권한 부여
//    if (user.isIsadmin()) {
//     grantedAuthorityList.add(new SimpleGrantedAuthority(Constant.ROLE_TYPE.ROLE_ADMIN.toString()));
// } else {
//     grantedAuthorityList.add(new SimpleGrantedAuthority(Constant.ROLE_TYPE.ROLE_USER.toString()));
// }

// 로그인 성공시 로그인 사용자 정보 반환
// return new MyAuthenticaion(id, password, grantedAuthorityList, user);
}

@Override
public boolean supports(Class<?> authentication) {
    System.out.println("xxxxxxxxxsupportsxxxxxxxxxxxx"+authentication);
return authentication.equals(UsernamePasswordAuthenticationToken.class);
//return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
}

}

