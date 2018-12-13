package com.platform.makeyourevent;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Collection;

@RestController
@RequestMapping("/rest/hello")
public class HelloResource {


    @GetMapping("/principal")
    public Principal user(Principal principal) {
    	System.out.println(principal+"chdfsdf");
        return principal;
    }
    
    @GetMapping
    public String hello(@AuthenticationPrincipal final UserDetails userDetails) {
    	System.out.println("at rest end point");
    	String username = userDetails.getUsername();
    	Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
    	authorities.stream().forEach(System.out::println);
        return "Hello World";
    }

}
