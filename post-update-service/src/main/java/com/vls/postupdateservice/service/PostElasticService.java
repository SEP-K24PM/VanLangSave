package com.vls.postupdateservice.service;

import com.vls.postupdateservice.dto.postelastic;
import com.vls.postupdateservice.repository.PostElasticRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostElasticService {
    private final PostElasticRepository postElasticRepository;

    @Autowired
    public PostElasticService(PostElasticRepository postElasticRepository) {
        this.postElasticRepository = postElasticRepository;
    }

    public postelastic update(postelastic postelastic) {
        return postElasticRepository.save(postelastic);
    }
}
