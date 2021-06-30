package com.vls.searchservice.service;

import com.vls.searchservice.model.PostElastic;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Service
public class PostService {
    public List<PostElastic> convertToPostElasticList(List<LinkedHashMap> list) throws ParseException {
        List<PostElastic> convertedResult = new ArrayList<PostElastic>();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        for (LinkedHashMap item: list) {
            convertedResult.add(new PostElastic(
                    item.get("id").toString(),
                    item.get("description").toString(),
                    item.get("exchange_methods").toString(),
                    df.parse(item.get("created_time").toString()),
                    item.get("thing_name").toString(),
                    item.get("origin").toString(),
                    item.get("category_name").toString()
            ));
        }
        return convertedResult;
    }
}
