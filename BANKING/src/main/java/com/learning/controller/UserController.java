package com.learning.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.entity.User;
import com.learning.repo.UserRepsitory;
import com.learning.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	

	 
	
	@Autowired
	UserRepsitory userrepo;
	@Autowired
	UserService userServ;

	@PostMapping("/adduser")
	User newUser(@RequestBody User user) {  	
	
		return userrepo.save(user);
	}

	
    @PutMapping("/updatuser/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") long id,@RequestBody User userDetails) {
       User updateUser = userrepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User Does not exist with id: " + id));

        updateUser.setFullName(userDetails.getFullName());
        updateUser.setPassword(userDetails.getPassword());

        userrepo.save(updateUser);

        return ResponseEntity.ok(updateUser);
    }

	@GetMapping ("/getUser")
	List<User>all(){
		return userrepo.findAll();
	}

	@GetMapping ("/getsortByFullName")
	List<User>findAllSortedByName(){

		//return userServ.performSorting();
		return userrepo.findAllSortedByfullName();
	}

	@GetMapping ("/getsortById")
	List<User>findAllSortedById(){
		return userrepo.findALLSortedByUserId();
	}


	@DeleteMapping("/deleteuser/{id}")
	public String deleteUser(@PathVariable("id") long id) {
		try {
			userrepo.deleteById(id);
			return "  USER DELETED SUCESSFULL!!";
		}catch(Exception e) {
			e.printStackTrace();
			return " UNABLE TO DELETE ID, PLEASE TRY AGAIN";
		}
	}

	@DeleteMapping("/deletUser/{fullName}")
	public String deleteName(@PathVariable("fullNmae") String fullName) {
		try {
			userrepo.deleteByUserfullName(fullName);
			return " DELETE SUCESSFULL!!";
		}catch(Exception e) {
			e.printStackTrace();
			return " UNABLE TO DELETE NAME, PLEASE TRY AGAIN";
		}
	}
	@DeleteMapping("/deletUser/{id}")
	public String deleteId(@PathVariable("id") long id) {
		try {
			userrepo.deleteByUserId(id);
			return " DELETE SUCESSFULL!!";
		}catch(Exception e) {
			e.printStackTrace();
			return " UNABLE TO DELETE NAME, PLEASE TRY AGAIN";
		}
	}
	
	
	
	

}
