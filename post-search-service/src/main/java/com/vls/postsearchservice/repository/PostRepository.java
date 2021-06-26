package com.vls.postsearchservice.repository;

import com.vls.postsearchservice.model.Post;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;
import java.util.UUID;

public interface PostRepository extends ElasticsearchRepository<Post, UUID> {
    List<Post> findBy(String name);
}
