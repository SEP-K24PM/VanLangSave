package com.vls.tradeservice.repository;

import com.vls.tradeservice.model.PostRegistration;
import com.vls.tradeservice.model.user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface PostRegistrationRepo extends JpaRepository<PostRegistration, UUID> {
    @Query("SELECT ele FROM PostRegistration as ele where ele.user_id = :userID")
    List<PostRegistration> listPostUserRegist(@Param("userID") UUID userID);

    @Query("SELECT info FROM user as info where info.email = :email")
    user giveUserID(@Param("email") String email);


}
