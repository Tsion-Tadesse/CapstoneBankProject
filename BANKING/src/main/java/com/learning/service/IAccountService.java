package com.learning.service;

import java.util.List;
import java.util.Optional;

import com.learning.entity.Account;

public interface IAccountService {
	
	
	public List<Account> findAllAccounts() ;

	public Optional<Account> findAccByAccNum(long accountNumber);
	
	public Account findByCustId(long customerId) ;

}
