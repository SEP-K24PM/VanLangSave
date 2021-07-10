package com.vls.tradeservice.repository;

import com.vls.tradeservice.model.PostRegistration;
import com.vls.tradeservice.model.user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface UserRepo extends JpaRepository<user, UUID> {

}
