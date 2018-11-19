package com.platform.makeyourevent.gatewayInterface;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.platform.makeyourevent.dataaccessService.DataAccessService;
import com.platform.makeyourevent.dataaccessService.DataAccessServiceImpl;
import com.platform.makeyourevent.model.Merchant;
import com.platform.makeyourevent.model.ResourceLink;


@Component
public class MerchantRequestHandler extends AbstractRequestHandler{
	
	private Gson gson = new Gson();
	
	public String type()
	{
		return "MYE_MERCHANT_REGISTER";
	}
	
	public  ResponseEntity handlePost(Map<String,Object> inputs)
	{
	   Type type = new TypeToken<Merchant>() {}.getType();
	   Merchant merchant = map(inputs.get("postData"),type);
	   try {
		Object jsonSchemaValid = schemaValidate(type,merchant);
		/*if(jsonSchemaValid!=null)
		{
			return new ResponseEntity<Object>(jsonSchemaValid, HttpStatus.BAD_REQUEST);
		}*/
	   addSelfLink(merchant,"v1");
	   final Merchant obj  = (Merchant) dataAccessService.persist(merchant);
	  
	   if(obj!=null)
		   return new ResponseEntity<Object>(obj,HttpStatus.CREATED);
	} catch (JsonProcessingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (ProcessingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   return null;
	}
	
	
	public ResponseEntity handleGET(Map<String,Object> inputs)
	{
		Map<String,Object> FilterCriteria = new HashMap();
		FilterCriteria.putAll((Map<String,Object>)inputs.get("queryParam"));
		FilterCriteria.putAll((Map<String,Object>)inputs.get("pathParams"));
		
		List<Merchant> merchant = dataAccessService.loadResources(Merchant.class,FilterCriteria);
		 return new ResponseEntity<Object>(merchant,HttpStatus.CREATED);
	}
	
	public ResponseEntity handlePUT(Map<String, Object> inputs) {
		// TODO Auto-generated method stub
		Map<String,Object> FilterCriteria = new HashMap();
		FilterCriteria.putAll((Map<String,Object>)inputs.get("queryParam"));
		FilterCriteria.putAll((Map<String,Object>)inputs.get("pathParams"));
		Merchant merchant = (Merchant) dataAccessService.updateResources(Merchant.class,FilterCriteria);
		return  new ResponseEntity<Object>(merchant,HttpStatus.OK);
	}
	
	
	public ResponseEntity handleDELETE(Map<String, Object> inputs) {
		Map<String,Object> FilterCriteria = new HashMap();
		FilterCriteria.putAll((Map<String,Object>)inputs.get("queryParam"));
		FilterCriteria.putAll((Map<String,Object>)inputs.get("pathParams"));
		Merchant merchant = (Merchant) dataAccessService.removeResources(Merchant.class,FilterCriteria);
		// TODO Auto-generated method stub
		return new ResponseEntity<Object>(merchant,HttpStatus.OK);
	}


	
   private Merchant addSelfLink(Merchant merchant,String version)
   {
	  ResourceLink rl = new ResourceLink("self", version+"/"+"merchant"+merchant.getId());
	  merchant.setSelfLink(rl);
	  return merchant;
   }
	

}
