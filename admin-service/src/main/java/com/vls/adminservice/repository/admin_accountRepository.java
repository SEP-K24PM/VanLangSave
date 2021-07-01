package com.vls.adminservice.repository;

import com.vls.adminservice.Module.Admin_account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface admin_accountRepository extends JpaRepository<Admin_account, String> {
    @Query("SELECT acc FROM Admin_account acc where acc.username = :name and acc.pwd = :pwd")
    List<Admin_account> checked(@Param("name") String username,@Param("pwd") String password);

    @Query("SELECT acc FROM Admin_account acc")
    List<Admin_account> findall();

    @Query("SELECT info.id FROM Admin_account info where info.id = :id")
    List<Admin_account> findAdminByID(@Param("id") String id);
}
