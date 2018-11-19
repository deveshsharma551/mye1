package com.platform.makeyourevent.internationalization;


import java.io.IOException;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Component;

/**
 * @author devesh
 *this class is to support internationalization and localization for display the user message to user from all the parts of the world
 */
@Component
public class Springi18nUtils {
	
	public static final String WILD_CARD_PATH = "META-INF/*-bundle/messages";
	
	@Autowired
	private ApplicationContext applicationContext;
	
	

	@Bean
	public MessageSource messageSource() throws IOException
	{
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		Resource[] r = resouceResolver();
		String[] s = new String[r.length];
		int i  = 0;
		for(Resource rs : r)
		{
			s[i++] = rs.getURL().getFile();
		}
		messageSource.setBasenames(s);
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}
	
	
	public String getMessage()
	{
		System.out.println("inside get message");
		System.out.println(Locale.US);
		return applicationContext.getMessage("object.not.found", null, null,Locale.US);
	}
	
	
	
	private Resource[] resouceResolver() throws IOException
	{
		PathMatchingResourcePatternResolver pathResolver = new PathMatchingResourcePatternResolver();
		return pathResolver.getResources("classpath*:"+WILD_CARD_PATH);
	}
}
