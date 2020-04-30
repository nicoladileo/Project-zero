package io.github.nicoladileo.security;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class AuthSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth) throws IOException, ServletException {
		Set<String> roles = AuthorityUtils.authorityListToSet(auth.getAuthorities());
        if (roles.size() > 0){
            response.sendRedirect("zul/main.zul");   
            return;
        }
        response.sendRedirect("accesso.zul");
	}    
}