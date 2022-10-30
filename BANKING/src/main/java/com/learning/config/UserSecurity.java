package com.learning.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;

import com.learning.repo.UserRepsitory;

public class UserSecurity {
	

	
	
	@Autowired
	UserRepsitory userRepo;
	
	public boolean hasUserId(Authentication authentication, Long Id) {
		
		Long userID=userRepo.findByUserName(authentication.getName()).getId();

            if(userID==Id)
            	return true;
            
            return false;
       
    }
	
	
	
	

}
