package com.platform.makeyourevent;


import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
public class DevConfig {
	
	DevConfig()
	{
		System.out.println("sdfsfsafsadfsdcheck");
	}
	
	@PostConstruct
	public void check() {
		System.out.println("+++++++++++++++++++++++++++dev environment check");
	}

}
