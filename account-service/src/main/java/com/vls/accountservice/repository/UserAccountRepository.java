package com.vls.accountservice.repository;

import com.vls.accountservice.model.User_Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserAccountRepository extends JpaRepository<User_Account, String> {

    @Query("SELECT user FROM User_Account user")
    List<User_Account> ListAllUser();
    Optional<User_Account> findAccountByEmailEquals(String email);
}
