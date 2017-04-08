package com.platform.makeyourevent.gatewayInterface;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.platform.makeyourevent.propertyHandler.PropertyUtils;

public class BaseGateWayInterface {
	
	@Autowired
	private PropertyUtils propertyUtils;
	
	
	public String handlerTypeResolver(String uriPattern)
	{
	   return propertyUtils.getStringPropertyValue(uriPattern);	 
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
	   String[] pathSplitter = path.split("/");
	   for(int i=0;i<=2;i++)
	   {
		   if(pathSplitter[i].contains("{")   && pathSplitter[i].contains("}"))
		   {
                pathParameters.put("id",pathSplitter[i].substring(pathSplitter[i].indexOf("{")+1, pathSplitter[i].indexOf("}")));		   
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
