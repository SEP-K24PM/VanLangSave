package com.vls.postdeleteservice.repository;

import com.vls.postdeleteservice.dto.postelastic;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostElasticRepository extends ElasticsearchRepository<postelastic, String> {
}
