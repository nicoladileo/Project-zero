package io.github.nicoladileo.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

public class AuthFailureHandler implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException auth) throws IOException, ServletException {
		String pageRedirect = "";
		if (auth instanceof BadCredentialsException)
			pageRedirect = "index.zul?login_error=Credenziali errate";
		else if (auth instanceof DisabledException)
			pageRedirect = "index.zul?login_error=Account scaduto";
		else 
			pageRedirect = "index.zul?login_error=Credenziali errate";
		response.sendRedirect(pageRedirect);
	}
}
