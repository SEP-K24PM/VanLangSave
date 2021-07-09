package com.vls.accountservice.repository;

import com.vls.accountservice.module.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface AccountRepository extends JpaRepository<Account, UUID> {

    @Query("SELECT user FROM Account user")
    List<Account> ListAllUser();
}
