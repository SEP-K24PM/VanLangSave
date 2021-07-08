package com.vls.accountservice.repository;

import com.vls.accountservice.module.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, String> {

    @Query("SELECT user FROM Account user")
    List<Account> ListAllUser();

    @Query("SELECT info.email FROM Account info where info.email = :email")
    List<Account> giveAccountInfo(@Param("email") String email);
}
