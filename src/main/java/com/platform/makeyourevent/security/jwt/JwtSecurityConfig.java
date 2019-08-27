package com.platform.makeyourevent.security.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.platform.makeyourevent.CorsFilter;

import io.jsonwebtoken.lang.Collections;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class JwtSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private JwtAuthenticationEntryPoint authenticationEntryPoint;
	
	@Autowired
	private JwtAuthenticationProvider authenticationProvider;
	
	@Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

	
	@Bean
	public JwtAuthenticationFilter jwtfilter() 
	{
		JwtAuthenticationFilter jwtfilter = null;
		try {
			jwtfilter = new JwtAuthenticationFilter("/registerBlogger",authenticationManager());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	return jwtfilter;
	}
	
	protected void configure(HttpSecurity http) throws Exception
	{
		http.csrf().disable()
		 .authorizeRequests().antMatchers("/registerBlogger").hasRole("USER").	
		 and().
		 exceptionHandling().authenticationEntryPoint(authenticationEntryPoint).
		 and().
		 sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		 .and().
		 addFilterBefore(new CorsFilter(), ChannelProcessingFilter.class)
		 .addFilterBefore(jwtfilter(),UsernamePasswordAuthenticationFilter.class);
	}
	
	 @Override
	 protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	     auth.authenticationProvider(authenticationProvider);
	 }
	
	


}
