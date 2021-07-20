package com.vls.tradeservice.service;

import com.vls.tradeservice.model.PostRegistWithEntities;
import com.vls.tradeservice.model.PostRegistration;
import com.vls.tradeservice.model.PostRegistrationWithPost;
import com.vls.tradeservice.repository.PostRegistrationRepo;
import com.vls.tradeservice.repository.PostRegistrationWithPostRepo;
import com.vls.tradeservice.repository.PostRegistrationWithRelatedEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PostRegistrationService {
    private final PostRegistrationWithRelatedEntityRepository postRegistrationWithRelatedEntityRepository;
    private final PostRegistrationRepo postRegistrationRepo;
    private final PostRegistrationWithPostRepo postRegistrationWithPostRepo;

    @Autowired
    public PostRegistrationService(PostRegistrationWithRelatedEntityRepository postRegistrationWithRelatedEntityRepository, PostRegistrationRepo postRegistrationRepo,
                PostRegistrationWithPostRepo postRegistrationWithPostRepo) {
        this.postRegistrationWithRelatedEntityRepository = postRegistrationWithRelatedEntityRepository;
        this.postRegistrationRepo = postRegistrationRepo;
        this.postRegistrationWithPostRepo = postRegistrationWithPostRepo;
    }

    public List<PostRegistWithEntities> getListPostRegis(UUID postId) {
        return postRegistrationWithRelatedEntityRepository.getListRegis(postId);
    }

    public PostRegistration register(PostRegistration postRegistration) {
        return postRegistrationRepo.save(postRegistration);
    }

    public Optional<PostRegistration> getPostRegis(UUID postRegisId) {
        return postRegistrationRepo.findById(postRegisId);
    }

    public void setChosen(PostRegistration postRegistration) {
        postRegistration.setChosen(true);
        postRegistrationRepo.save(postRegistration);
    }

    public List<PostRegistrationWithPost> getUserRegisList(UUID userId) {
        return postRegistrationWithPostRepo.getUserRegisList(userId);
    }
}
