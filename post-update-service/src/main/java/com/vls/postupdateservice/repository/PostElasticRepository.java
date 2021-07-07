package com.vls.postupdateservice.repository;

import com.vls.postupdateservice.dto.postelastic;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostElasticRepository extends ElasticsearchRepository<postelastic, String> {
}
