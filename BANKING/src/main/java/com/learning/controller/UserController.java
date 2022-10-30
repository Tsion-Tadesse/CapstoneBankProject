package com.learning.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.learning.entity.Account;
import com.learning.entity.User;
import com.learning.service.UserService;

@RestController
@RequestMapping("/api")
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class UserController {
	
	
	
	
	@Autowired
	UserService userService;
	
	@PreAuthorize("hasRole('ROLE_STAFF') or hasRole('ROLE_ADMIN')")
	@GetMapping("/users")
	public List<User> getAllUsers(Authentication authentication) {
		
		return userService.findAllUsers();
		
	}
		
	@PostMapping("/user")
	public ResponseEntity<User> saveusers(@RequestBody User newUser,Authentication auth) {
		System.out.println(newUser.getFullName()+"  "+auth.getName());
		return ResponseEntity.status(HttpStatus.CREATED).body((userService.saveUser(newUser)));
		
	}
	
	@PreAuthorize("@userSecurity.hasUserId(authentication,#id)")
	@GetMapping("/user/{id}")
	public ResponseEntity<User> getUserById(@PathVariable("id") long id, Authentication authentication) {
		System.out.println("Inside getuserbyid method");
		return ResponseEntity.ok().body(userService.findUserById(id).get());
		
	}
	
	@PutMapping("/user/{id}")
	

	
	
	
	
	public ResponseEntity<User> updateUser(@PathVariable("userId") long id,@RequestBody User newUser) {
		return ResponseEntity.ok().body(userService.updateUser(id,newUser));
		
	}
	
	
	@DeleteMapping("/user/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable("id") long id) {
		 userService.deleteUser(id);
		 return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		
	}
	
	@GetMapping("/user/search")
	@PostAuthorize("returnObject.body.username==authenticated.user")
	public ResponseEntity<User> userDetails(Authentication authentication, @RequestParam("fullName") String fullName) throws Exception {
		System.out.println(authentication.getName().toString());
		User User=userService.findByUserName(fullName);
		if(User==null) {
			ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
		}
		return ResponseEntity.ok().body(User);
		
	}
	
	
	 
	
	
	

}
