package com.vls.postsearchservice.repository;

import com.vls.postsearchservice.model.Post;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PostRepository extends ElasticsearchRepository<Post, UUID> {
    List<Post> findBy(String name);
}
