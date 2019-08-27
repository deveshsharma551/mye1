package com.platform.makeyourevent.security.jwt;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;

@Component
public class JwtAuthenticationProvider  implements AuthenticationProvider{

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		// TODO Auto-generated method stub
	    JwtUser claims = (JwtUser) authentication.getPrincipal();
	    if(claims.getUserName().equals("devesh")) {
	    	 List<GrantedAuthority> authorities = new ArrayList<>();
	    	 authorities.add(new SimpleGrantedAuthority("ROLE_"+claims.getRole()));
	    	return new UsernamePasswordAuthenticationToken(claims.getUserName(),null,authorities);
	    }
		return null;
	}

	@Override
	public boolean supports(Class<?> authentication) {
			return authentication.equals(
		          UsernamePasswordAuthenticationToken.class);
	}

}
