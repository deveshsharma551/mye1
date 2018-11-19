package com.platform.makeyourevent.gatewayInterface;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.platform.makeyourevent.handleHandlerConfiguration.HandleAllHandlerConfiguration;
import com.platform.makeyourevent.propertyHandler.PropertyUtils;

public class BaseGateWayInterface {
	
	@Autowired
	private PropertyUtils propertyUtils;
	
	@Autowired
	private HandleAllHandlerConfiguration handleAllConfiguration;
	
	
	public String handlerTypeResolver(String uriPattern)
	{
	  if(propertyUtils.getStringPropertyValue(uriPattern)!=null)
	 {
	   return propertyUtils.getStringPropertyValue(uriPattern);	 
	 }
	  else
	  {
		  return handleAllConfiguration.handlerTypeResolver(uriPattern);
	  }
	  
	  
	}

	
	public Map<String,String> getQueryParams(HttpServletRequest request)
	{
		Map<String,String> queryParameters = new HashMap<String,String>();
		for(Map.Entry<String,String[]> query : request.getParameterMap().entrySet())
		{
			queryParameters.put(query.getKey(),query.getValue()[0]);
		}
		return queryParameters;
	}
	
	public Map<String,String> getPathParams(String path)
	{
		Map<String,String> pathParameters = new HashMap<String,String>();
		String interfacePath   = handleAllConfiguration.getTheMatchingPath(path);
	   String[] pathSplitter = path.split("/");
	   String[] interfacePathSplitter = interfacePath.split("/");
	   
	   for(int i=0;i<pathSplitter.length;i++)
	   {
		   if(!(pathSplitter[i].equals(interfacePathSplitter[i])))
		   {
                pathParameters.put("id",pathSplitter[i]);		   
		   }
	   }
		 return pathParameters;
	}
	
	public Map<String,Object> prepareParams(Object queryParams, Object pathParam, String postData, String method)
	{
	   Map<String,Object> inputParams = new HashMap<String,Object>();
	   inputParams.put("queryParam",queryParams);
	   inputParams.put("pathParams",pathParam);
	   inputParams.put("postData", postData);
	   inputParams.put("methodName", method);
	   return inputParams;
	}
	
}
