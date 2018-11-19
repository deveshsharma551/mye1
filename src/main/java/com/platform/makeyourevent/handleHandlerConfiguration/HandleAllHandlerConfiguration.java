package com.platform.makeyourevent.handleHandlerConfiguration;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.io.support.*;
import org.springframework.stereotype.Component;

@Component
public class HandleAllHandlerConfiguration {
	
	
  @Autowired
  private Environment env;
	
 private Map<String,Object> propertiesList = new HashMap<String,Object>();
	
     @PostConstruct
	 public  void loadHandlerCongiurationIntoList()
		{	
		 for(Iterator it = ((AbstractEnvironment)env).getPropertySources().iterator();it.hasNext();)
			{
				org.springframework.core.env.PropertySource propertySource = (org.springframework.core.env.PropertySource) it.next();	
				if(propertySource instanceof ResourcePropertySource)
				{
					Map<String,Object> values = ((MapPropertySource)propertySource).getSource();
					for(Entry<String,Object> value : values.entrySet())
					{
						propertiesList.put(value.getKey(),value.getValue());
					}
				}
			}
		}
		
		 public String getTheMatchingPath(String path)
		 {
		        for(String key: propertiesList.keySet())
		        {
		        	if(isExpressionMatched(path,key))
	            { 
		                return key;
	            }

		        }
		        return null;
		    }
		 
		 private boolean isExpressionMatched(String path,String interfacePath)
		 {
			 String[] pathArray = path.split("/");
			 String[] pathKey = interfacePath.split("/");
			 if(pathArray.length!=pathKey.length)
			 {
				 return false;
			 }
			 for(int iterator= 0;iterator<pathArray.length;iterator++)
			 {
			 if(pathKey[iterator].equals(pathArray[iterator]) || pathKey[iterator].contains("}") && pathKey[iterator].contains("{"))
	         	{
	     		continue;
	     	    }
			 else
			 {
				 return false;
			 }
			 

}
			 return true;
		 }
		 
		 public String handlerTypeResolver(String uriPattern)
			{
			 for(String key : propertiesList.keySet())
			 {
				 if(isExpressionMatched(uriPattern,key))
				 {
					 return (String) propertiesList.get(key);
				 }
			 }
			 return null;
			   
			 }
}
