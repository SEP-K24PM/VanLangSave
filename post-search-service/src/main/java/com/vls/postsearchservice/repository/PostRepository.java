package com.vls.postsearchservice.repository;

import com.vls.postsearchservice.model.PostElastic;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends ElasticsearchRepository<PostElastic, String> {
    List<PostElastic> findBy(String name);
}
