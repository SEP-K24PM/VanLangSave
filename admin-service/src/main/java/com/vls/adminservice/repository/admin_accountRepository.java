package com.vls.adminservice.repository;

import com.vls.adminservice.Module.Admin_account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface admin_accountRepository extends JpaRepository<Admin_account, String> {
    @Query("SELECT acc FROM Admin_account acc where acc.username = ?1 and acc.pwd = ?2")
    List<Admin_account> checked(String username, String password);

    @Query("SELECT acc FROM Admin_account acc")
    List<Admin_account> findall();
}
