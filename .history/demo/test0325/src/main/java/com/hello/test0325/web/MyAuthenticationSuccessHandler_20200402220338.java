package com.hello.test0325.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.GrantedAuthority;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class MyAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler{

// 세션 생성
// 쿠키 생성

@Override
public void onAuthenticationSuccess(HttpServletRequest request,
HttpServletResponse response, Authentication auth)throws IOException, ServletException {
    System.out.println(">>>>> MyAuthenticationSuccessHandler <<<<<");
  
    List<GrantedAuthority> authorities =  (List<GrantedAuthority>) auth.getAuthorities();
    String strAuth = authorities.get(0).getAuthority();

    Cookie cookie = new Cookie("auth", strAuth);
    cookie.setPath("/");
    response.addCookie(cookie);



    // response.setStatus(HttpServletResponse.SC_OK);
    // // 성공 시 response를 json형태로 반환
    // response.getWriter().print("{\"success\": true}");
    // response.getWriter().flush();

    
    if(strAuth.equals("ROLE_ADMIN")){
        response.sendRedirect(request.getContextPath() +  "/admin");
    }else{
        response.sendRedirect(request.getContextPath() +  "/hello");
    }
    
    

}
}
