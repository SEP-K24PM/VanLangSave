package com.vls.managementservice.repository;

import com.vls.managementservice.model.AdminAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AdminAccountRepository extends JpaRepository<AdminAccount, UUID> {
    public Optional<AdminAccount> findAdmin_AccountByEmailEquals(String email);
}
