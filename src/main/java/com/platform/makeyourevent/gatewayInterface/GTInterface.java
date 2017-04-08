package com.platform.makeyourevent.gatewayInterface;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.platform.makeyourevent.internationalization.Springi18nUtils;
import com.platform.makeyourevent.model.Merchant;
import com.sun.mail.handlers.handler_base;

@RestController
@RequestMapping("/**")
public class GenericGateWayInterface extends BaseGateWayInterface{
	
    
    @Autowired
    private RequestHandlerFactory requestHandlerFactory;
    
    @Autowired
    private Springi18nUtils springi18nUtils;
	
	@PostMapping(produces=MediaType.APPLICATION_JSON_VALUE , consumes = MediaType.APPLICATION_JSON_VALUE)
	public  ResponseEntity create(@RequestParam Map<String,Object> queryParameters, HttpServletRequest request, @RequestBody String postParam ) throws MalformedURLException
	{		
		//System.out.println(springi18nUtils.getMessage());  
    	return getRequestHandler(queryParameters,request,postParam,"POST");
	}
	
	
	
	private ResponseEntity getRequestHandler(Map<String,Object> queryParam,HttpServletRequest request,String postParam,String methodName)
	{
		  String typeOfServiceHandler = handlerTypeResolver(request.getRequestURI().substring(1));		
          Map<String,String> pathParams = getPathParams(request.getRequestURI().toString());
          Map<String,Object> inputParams = prepareParams(queryParam,pathParams,postParam,methodName);
 	      ServiceHandler serviceHandler = requestHandlerFactory.getServiceHandler(typeOfServiceHandler); 	    
		 return serviceHandler.handleRequests(inputParams) ;
	}
	
}
