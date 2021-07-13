package com.vls.tradeservice.repository;

import com.vls.tradeservice.model.PostRegistWithEntities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PostRegistrationWithRelatedEntityRepository extends JpaRepository<PostRegistWithEntities, UUID> {
    @Query("SELECT ele from PostRegistWithEntities as ele where ele.post_id = :postID")
    List<PostRegistWithEntities> getListRegis(@Param("postID") UUID postID);
}
