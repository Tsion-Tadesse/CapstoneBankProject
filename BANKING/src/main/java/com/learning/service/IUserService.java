package com.learning.service;

import java.util.List;
import java.util.Optional;

import com.learning.entity.User;

public interface IUserService {
	
	public List<User> findAllUsers() ;

	public Optional<User> findUserById(long id);
	
	public User findByUserName(String userName) ;

}
