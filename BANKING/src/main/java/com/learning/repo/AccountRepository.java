package com.learning.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

}
