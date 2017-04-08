package com.platform.makeyourevent.propertyHandler;

import java.text.Normalizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;



@Component
@PropertySource("classpath:Database.properties")
@PropertySource("classpath:HandlerConfiguration.properties")
public class PropertyUtils {

	@Autowired
	private Environment env;
	
	public String getStringPropertyValue(String propertyKey)
	{
		return env.containsProperty(propertyKey)==false ? null:Normalizer.normalize(env.getProperty(propertyKey),Normalizer.Form.NFKC);
	}
	
	public Integer getIntegerPropertyValue(String propertyKey)
	{
		return env.containsProperty(propertyKey)==false ? null:Integer.parseInt(env.getProperty(propertyKey));
	}
}
