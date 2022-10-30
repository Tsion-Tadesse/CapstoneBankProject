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

import com.learning.entity.Account;
import com.learning.repo.AccountRepository;
import com.learning.service.AccountService;

public class AccountController {
	
	
	
	
	
	@Autowired
	AccountRepository accRepo;
	@Autowired
	AccountService accService;

	@PostMapping("/account")
	Account newAccount(@RequestBody Account account) {  	
	
		return accRepo.save(account);
	}

	
    @PutMapping("/updateaccount/{id}")
    public ResponseEntity<Account> updateAccount(@PathVariable("accountNumber") long accountNumber ,@RequestBody Account accountDetails) {
       Account updateAccount = accRepo.findById(accountNumber)
                .orElseThrow(() -> new RuntimeException("Account  not exist with accountNumber: " + accountNumber));

        updateAccount.setAccountType(accountDetails.getAccountType());
        updateAccount.setAccountNumber(accountDetails.getAccountNumber());
        updateAccount.setCustomerId(accountDetails.getCustomerId());

        accRepo.save(updateAccount);

        return ResponseEntity.ok(updateAccount);
    }

	@GetMapping ("/getaccount")
	
	List<Account>all(){
		return  accRepo.findAll();
	}

	@GetMapping ("/getsortByAccName")
	List<Account>findAllSortedByCustId(){

		//return accService.performSorting();
		return accRepo.findALLSortedByCustId();
	}

	@GetMapping ("/getsortByAccNum")
	List<Account>findAllSortedByAccNum(){
		return accRepo.findAllSortedByAccNum();
	}


	@DeleteMapping("/deleteaccount/{id}")
	public String deleteAccount(@PathVariable("accountNumber") long accountNumber) {
		try {
			accRepo.deleteByCustId(accountNumber);
			return "  ACOOUNT DELETED SUCESSFULL!!";
		}catch(Exception e) {
			e.printStackTrace();
			return " UNABLE TO DELETE ACCOUNT, PLEASE TRY AGAIN";
		}
	}

}
	
	
	

	


