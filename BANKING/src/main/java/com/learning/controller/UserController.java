package com.learning.controller;

import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.validation.annotation.Validated;
import com.learning.entity.User;
import com.learning.repo.UserRepository;

@RequestMapping("/api/customer")
@RestController
public class UserController {

	@Autowired
	UserRepository userRepository;

	// register User
	@PostMapping("/register")
	User register(@RequestBody User user) {
		return userRepository.save(user);
	}

	/*
	 * @GetMapping("/customerById{id}")
	 * User findUserById(@Valid @RequestBody User user, @PathVariable("id") long id)
	 * {
	 * return userRepository.findById(id);
	 * }
	 */
}
