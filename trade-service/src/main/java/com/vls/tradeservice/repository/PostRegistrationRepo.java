package com.vls.tradeservice.repository;

import com.vls.tradeservice.model.PostRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface PostRegistrationRepo extends JpaRepository<PostRegistration, UUID> {


}
