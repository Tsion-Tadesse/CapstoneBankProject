package com.learning.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.learning.entity.User;
import com.learning.entity.UserDetailsImpl;
import com.learning.repo.UserRepsitory;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	
		@Autowired
		private UserRepsitory userRepo;

		@Override
		public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
			
			User user=userRepo.findByUserName(username);
			
			
			if(user==null) {
				System.out.println("exception thrown");
				throw new UsernameNotFoundException(username + "not found");
			}
			return new UserDetailsImpl(user);
		}

}
