package com.vls.postduplicationservice.repository;

import com.vls.postduplicationservice.dto.PostElastic;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends ElasticsearchRepository<PostElastic, String> {
}
