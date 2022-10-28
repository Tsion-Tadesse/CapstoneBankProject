package com.learning.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.entity.Password;

public interface PasswordRepository extends JpaRepository<Password, Long> {

}
