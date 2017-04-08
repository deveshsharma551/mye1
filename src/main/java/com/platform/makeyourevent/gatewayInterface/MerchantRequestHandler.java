package com.platform.makeyourevent.gatewayInterface;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.platform.makeyourevent.model.Merchant;


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

	

	

}
