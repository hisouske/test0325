package com.hello.test0325.web;


public class CustomAuthenticationSuccess implements AuthenticationSuccessHandler {

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth)
            throws IOException, ServletException {
        System.out.println("Entered Authentication Successful Method");
        boolean isUser = false;
        Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
        String targetUrl = null;
        for(GrantedAuthority currentAuth : authorities){
            if(currentAuth.getAuthority().equalsIgnoreCase("ROLE_USER")){
                isUser = true;
                break;
            }
            else {
                throw new IllegalStateException();
            }
        }

        if(isUser){
            targetUrl = "/vendor";
        }
        System.out.println("Entered Authentication Successful Method");
        redirectStrategy.sendRedirect(request, response, targetUrl);
    }
}