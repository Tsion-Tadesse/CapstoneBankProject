package com.learning.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.entity.Account;
import com.learning.repo.AccountRepository;

@Service
public class  AccountService  implements IAccountService{
	
	
	
	@Autowired
	private AccountRepository accRepo;
	
	
	public List<Account> findAllAccounts() {
		return accRepo.findAll();
	}


	public Optional<Account> findAccByAccNum(long accountNumber ) {
		return accRepo.findById(accountNumber);
	}
	
	public Account findByCustId(long customerId) {
		
		Account account =accRepo.findByCustId(customerId) ;
		
		return account;
		
	}
	
	public Account saveAccount(Account newAccount) {
		
		Account account=accRepo.save(newAccount);
		return account;
		
	}

	public Account updateAccount(long accountNumber,Account acc) {
		
		Optional<Account> retrievedAccount=accRepo.findById(accountNumber);
		
		if(retrievedAccount==null)
			try {
				throw new Exception("Account  not found");
			} catch (Exception e) {
				e.printStackTrace();
			}
		accRepo.save(acc);
		return accRepo.findById(accountNumber).get();
		
	}
	
	public Account delete(long accountNumber) {
		
		Optional<Account> retrievedAccount=accRepo.findById(accountNumber);
		if(retrievedAccount==null)
			try {
				throw new Exception("Account not found");
			} catch (Exception e) {
				e.printStackTrace();
			}
		accRepo.deleteByAccNum(accountNumber);
		return retrievedAccount.get();
		
		
		
	}
	
	
	

}
