package com.vls.postsearchservice.repository;

import com.vls.postsearchservice.dto.postelastic;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends ElasticsearchRepository<postelastic, String> {
    List<postelastic> findBy(String name);
}
