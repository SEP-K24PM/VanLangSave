package com.vls.postsearchservice.repository;

import com.vls.postsearchservice.dto.postelastic;
import org.elasticsearch.common.unit.Fuzziness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static org.elasticsearch.index.query.QueryBuilders.multiMatchQuery;

@Repository
public class PostDAOImpl implements PostDAO {


    private final ElasticsearchRestTemplate elasticSearchRestTemplate;

    @Autowired
    public PostDAOImpl(ElasticsearchRestTemplate elasticSearchRestTemplate) {
        this.elasticSearchRestTemplate = elasticSearchRestTemplate;
    }

    @Override
    public List<postelastic> search(String search) {
        NativeSearchQuery searching = new NativeSearchQueryBuilder()
                .withQuery(
                        multiMatchQuery(search)
                                .field("thing_name", 4.0f)
                                .field("category_name", 3.0f)
                                .field("description", 2.0f)
                                .field("origin", 1.0f)
                                .field("exchange_methods", 1.0f)
                                .fuzziness(Fuzziness.AUTO)
                                .prefixLength(3)
                                .fuzzyTranspositions(true)
                )
                .build();
        SearchHits<postelastic> postSearchHits = elasticSearchRestTemplate.search(
                searching,
                postelastic.class,
                IndexCoordinates.of("postelastic"));
        List<postelastic> postelastics = convertToListPostelastic(postSearchHits);
        return postelastics;
    }

    public List<postelastic> convertToListPostelastic(SearchHits<postelastic> searchHits) {
        List<postelastic> postelastics = new ArrayList<>();
        for (int i = 0; i < searchHits.getTotalHits(); i++) {
            postelastics.add(searchHits.getSearchHit(i).getContent());
        }
        return postelastics;
    }
}
