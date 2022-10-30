package com.learning.repo;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
//import org.springframework.transaction.annotation.Transactional;

import com.learning.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

	@Query(value = "SELECT e FROM Account e ORDER  By customerId")
	public List <Account> findALLSortedByCustId();
	
	@Query(value = "SELECT c FROM Account WHERE c.customerId =:customerId")
	public Account findByCustId(@Param("customerId ")long customerId);
		
		@Query(value = "Select * from Account order by accountNumber", nativeQuery=true)
		public  List <Account> findAllSortedByAccNum();
		//List<Employee>findAllSortedById();
		
		
		//@Modifying 
		//@Transactional
		@Query(value = "Delete from Account e   where  e. customerId = :customerId")
		public  void deleteByCustId(@Param("customerId ")long customerId);

		@Query(value = "Delete from Account e   where  e. accountNumber = :accountNumber")
		public  void deleteByAccNum(@Param("accountNumber ")long accountNumber);

		

}
//List<Account> findAllSortedById();