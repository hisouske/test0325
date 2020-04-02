package com.hello.test0325.web;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

public class SecurityWebApplicationInitializer 
extends AbstractSecurityWebApplicationInitializer { 


    @Override protected Class<?>[] getRootConfigClasses() { return new Class[] { WebSecurityConfig.class }; }

}

