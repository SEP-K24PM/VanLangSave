package com.vls.admin_usermanagementservice.repository;

import com.vls.admin_usermanagementservice.model.userAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserAccountRepo extends JpaRepository< userAccount, UUID> {

}
