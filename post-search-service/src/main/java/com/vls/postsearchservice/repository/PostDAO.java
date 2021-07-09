package com.vls.postsearchservice.repository;

import com.vls.postsearchservice.dto.postelastic;
import org.springframework.data.elasticsearch.core.SearchHits;

import java.util.List;

public interface PostDAO {
    public List<postelastic> search(String search);
}
