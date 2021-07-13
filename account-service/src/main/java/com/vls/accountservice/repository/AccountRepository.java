package com.vls.accountservice.repository;

import com.vls.accountservice.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {

    @Query("SELECT user FROM Account user")
    List<Account> ListAllUser();

    @Query("SELECT info FROM Account as info where info.id = :id")
    Account giveAccountInfo(@Param("id") UUID id);

    //@Query("SELECT info.email FROM Account as info where info.email = :email")
    //List<Account> CheckedUser(@Param("email") String email); //check tài khoản đã tồn tại chưa

}
