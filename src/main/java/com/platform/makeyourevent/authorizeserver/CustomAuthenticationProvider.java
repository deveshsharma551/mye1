/*package com.platform.makeyourevent.authorizeserver;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
 
    @Override
    public Authentication authenticate(Authentication authentication) 
      throws AuthenticationException {
    	
    	Collection<GrantedAuthority> grantedAuths = new ArrayList();
    	grantedAuths.add(new SimpleGrantedAuthority("ROLE_TRUSTED_CLIENT"));
         return new UsernamePasswordAuthenticationToken("user", "password", new ArrayList());
        }
 
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(
          UsernamePasswordAuthenticationToken.class);
    }
}*/