package com.learning.repo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.learning.entity.User;

@Repository
public interface UserRepsitory extends JpaRepository<User, Long>{

}