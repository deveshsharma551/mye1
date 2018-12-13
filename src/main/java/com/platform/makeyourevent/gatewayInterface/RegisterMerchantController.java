package com.platform.makeyourevent.gatewayInterface;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.platform.makeyourevent.model.RegisteredBloggers;

@RestController
@RequestMapping("/registerBlogger")
@CrossOrigin(origins = "http://localhost:8080")
public class RegisterMerchantController {
	
	
	@PostMapping(produces=MediaType.APPLICATION_JSON_VALUE,consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RegisteredBloggers> handleRegistration(@RequestBody RegisteredBloggers bloggers)
	{
		System.out.println("inside"+bloggers.getFirstName());
		return  ResponseEntity.ok().body(bloggers);
	}

}
