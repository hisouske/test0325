
package com.hello.test0325.web;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    int cnt = 0;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
      cnt ++;
        String username = (String) authentication.getPrincipal();
        String webpassword= (String)authentication.getCredentials();
    
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        
        List<User> user = userService.getUsername(username);
        System.out.println(user.size());
        // user가 없거나 비밀번호가 맞지 않는 경우.
        if (user.size()>0 && webpassword!=null) {
            String dbpassword  = user.get(0).getPassword();
            System.out.println(dbpassword +"/" + webpassword +"/"+ cnt);
            System.out.println(passwordEncoder.matches(webpassword, dbpassword));
         if (!passwordEncoder.matches(webpassword, dbpassword)) {
           System.out.println("-------this ------");
            return null;
        }
    }else{
        return null;
    }
System.out.println(authentication);
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
return authentication.equals(UsernamePasswordAuthenticationToken.class);
}

}

