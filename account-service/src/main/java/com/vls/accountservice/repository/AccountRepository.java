package com.vls.accountservice.repository;

import com.vls.accountservice.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {

    @Query("SELECT user FROM Account user")
    List<Account> ListAllUser();

    @Query("SELECT info.email FROM Account as info where info.email = :email")
    Account giveAccountInfo(@Param("email") String email);

    //@Query("SELECT info.email FROM Account as info where info.email = :email")
    //List<Account> CheckedUser(@Param("email") String email); //check tài khoản đã tồn tại chưa

}
