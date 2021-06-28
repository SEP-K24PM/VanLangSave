package com.vls.postduplicationservice.repository;

import com.vls.postduplicationservice.model.Post;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PostRepository extends ElasticsearchRepository<Post, UUID> {
}
