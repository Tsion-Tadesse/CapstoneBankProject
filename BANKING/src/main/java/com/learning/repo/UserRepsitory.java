package com.learning.repo;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import com.learning.entity.User;

@Repository
public interface UserRepsitory extends JpaRepository<User, Long>{
	
	User findByUserName(String userName);
	
	
	@Query(value = "SELECT e FROM User e ORDER  By id")
	public List <User> findALLSortedByUserId();
		
		
		@Query(value = "Select * from User order by fullName", nativeQuery=true)
		public  List <User> findAllSortedByfullName();
	
		@Query(value = "Delete from User e   where  e. fullName = :fullName")
		public  void deleteByUserfullName(@Param("id")String fullNmae);
		
		@Modifying 
		@Transactional
		@Query(value = "Delete from User e   where  e. id = :id")
		public  void deleteByUserId(@Param("id")long id);
	


}
