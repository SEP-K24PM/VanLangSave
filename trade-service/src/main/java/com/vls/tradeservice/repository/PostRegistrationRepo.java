package com.vls.tradeservice.repository;

import com.vls.tradeservice.model.PostRegistration;
import com.vls.tradeservice.model.user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface PostRegistrationRepo extends JpaRepository<PostRegistration, UUID> {
   @Query("SELECT ele from PostRegistration as ele where ele.post_id = :postID")
   List<PostRegistration> giveListRegister(@Param("postID") UUID postID);

}
