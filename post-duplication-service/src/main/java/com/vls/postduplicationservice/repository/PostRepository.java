package com.vls.postduplicationservice.repository;

import com.vls.postduplicationservice.dto.postelastic;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends ElasticsearchRepository<postelastic, String> {
}
