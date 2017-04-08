package com.platform.makeyourevent.internationalization;


import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

/**
 * @author devesh
 *this class is to support internationalization and localization for display the user message to user from all the parts of the world
 */
@Component
public class Springi18nUtils {
	
	@Autowired
	private ApplicationContext applicationContext;
	
	

	@Bean
	public MessageSource messageSource()
	{
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath*:messages");
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}
	
	
	public String getMessage()
	{
		System.out.println("inside get message");
		System.out.println(Locale.US);
		return applicationContext.getMessage("object.not.found", null, null,Locale.US);
	}
}
