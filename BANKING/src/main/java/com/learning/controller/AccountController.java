package com.learning.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostFilter;
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
import com.learning.service.AccountService;


@RestController
@RequestMapping("/api")
public class AccountController {
	
	
	
	

	
	
	@Autowired
	AccountService accServ;
	
	@GetMapping("/account")
	public ResponseEntity<List<Account>> getAllAccount() {
		return ResponseEntity.ok().body( accServ.findAllAccounts());
		
	}
	
	@GetMapping("/account/owned")
	@PostFilter("filterObject.owner==authentication.name")
	public List<Account> getAccountOwnedBy() {
		return  accServ.findAllAccounts();
		
	}
	
	@PostMapping("/account")
	public ResponseEntity<Account> saveAccount(@RequestBody Account newAccount,Authentication auth) {
		System.out.println(newAccount.getCustomerId()+"  "+auth.getName());
		return ResponseEntity.status(HttpStatus.CREATED).body(( accServ.saveAccount(newAccount)));
		
	}
	
	@GetMapping("/account/{cutomerId}")
	public ResponseEntity<Account> getByCustId(@PathVariable("customerId") long customerId) {
		return ResponseEntity.ok().body(accServ.findByCustId(customerId));
				
		
	}
	
	@PutMapping("/account/{cutomerId}")
	public ResponseEntity<Account> updateAccount(@PathVariable("customerId") long customerId,@RequestBody Account newAccount) {
		return ResponseEntity.ok().body( accServ.updateAccount(customerId,newAccount));
		
	}
	
	@DeleteMapping("/account/{customerId}")
	public ResponseEntity<Account> deleteAccount(@PathVariable("id") long customerId) {
		  accServ.delete(customerId);
		 return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		
	}
	
	@GetMapping("/account/search")
	public ResponseEntity<?> userDetails(Authentication auth, @RequestParam("accountNumber") long accountNumber)
	{
		System.out.println(auth.getName().toString());
		Account account= accServ.findByCustId(accountNumber);
		if(account==null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Account not found");
		}
		return ResponseEntity.ok().body(account);
		
	}
	
	
	
	
	
	
}
	
	
	

	


