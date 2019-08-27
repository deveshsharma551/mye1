package com.platform.makeyourevent.gatewayInterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.platform.makeyourevent.dataaccessService.DataAccessService;
import com.platform.makeyourevent.model.RegisteredBloggers;
import com.platform.makeyourevent.model.User;

@RestController
@RequestMapping("/registerBlogger")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RegisterMerchantController {
	
	
	@Autowired
	public DataAccessService dataAccessService;
	
	@PostMapping(produces=MediaType.APPLICATION_JSON_VALUE,consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RegisteredBloggers> handleRegistration(@RequestBody RegisteredBloggers bloggers)
	{
		Map<String,Object> FilterCriteria = new HashMap();
		FilterCriteria.put("username","devesh");
		List<User> users = dataAccessService.loadResources(User.class, FilterCriteria);
		System.out.println(users.get(0).getPassword());
		return  ResponseEntity.ok().body(bloggers);
	}

}
