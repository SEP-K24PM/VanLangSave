package com.vls.adminservice.repository;

import com.vls.adminservice.model.Admin_Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AdminAccountRepository extends JpaRepository<Admin_Account, UUID> {
    public Optional<Admin_Account> findAdmin_AccountByEmailEquals(String email);
}
