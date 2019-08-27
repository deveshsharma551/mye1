package com.platform.makeyourevent.dataaccessService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.platform.makeyourevent.model.CustomUserDetail;
import com.platform.makeyourevent.model.User;


@Service
public class CustomUserDetailService implements UserDetailsService {
	
	
	@Autowired
	public DataAccessService dataAccessService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//System.out.println("inside"+bloggers.getFirstName());
		Map<String,Object> FilterCriteria = new HashMap();
		FilterCriteria.put("username",username);
		User user = dataAccessService.loadResource(User.class, FilterCriteria);
		System.out.println(user.getPassword());
		return new CustomUserDetail(user);
	}

}
