package com.platform.makeyourevent.gatewayInterface;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

@Component
public class RequestHandlerFactory {
	
private Map<String,ServiceHandler> serviceHandlerList = new HashMap();
	

    public  ServiceHandler getServiceHandler(String type) {
		return serviceHandlerList.get(type);
	}



	public void registerAllHandler(ServiceHandler serviceHandler)
	{
		serviceHandlerList.put(serviceHandler.type(),serviceHandler);
	}
}
