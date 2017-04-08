package com.platform.makeyourevent.gatewayInterface;

import java.util.Map;

import org.springframework.http.ResponseEntity;

public interface ServiceHandler {
	
	public String type();
	
	public ResponseEntity handleRequests(Map<String,Object> inputs);
	

}
