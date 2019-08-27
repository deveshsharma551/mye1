package com.platform.makeyourevent.security.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class JwtAuthenticationFilter extends AbstractAuthenticationProcessingFilter{
	
	
	private String secret="youtube";
	
	@Autowired
	private JwtValidator jwtValidator;
	


	protected JwtAuthenticationFilter(String defaultFilterProcessesUrl,AuthenticationManager manager) {
		super(defaultFilterProcessesUrl);
		setAuthenticationManager(manager);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		// TODO Auto-generated method stub
		String authtoken = request.getHeader("authorization");
		String jwtToken = authtoken.substring(7,authtoken.length());
		JwtUser user = jwtValidator.validate(jwtToken);
		//Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(jwtToken).getBody();
		return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(user, null, null)) ;
	}
	
	@Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
       // super.successfulAuthentication(request, response, chain, authResult);
        System.out.println(authResult);
        SecurityContextHolder.getContext().setAuthentication(authResult);
        chain.doFilter(request, response);
    }
}



