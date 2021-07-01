package com.vls.searchservice;

import com.vls.searchservice.controller.SearchController;
import com.vls.searchservice.dto.postelastic;
import com.vls.searchservice.service.PostService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(SearchController.class)
public class SearchControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private PostService postService;

    @MockBean
    @Autowired
    private RestTemplate restTemplate;

    @Test
    public void testPostSearch() throws ParseException {
        String search = "search";

        List<LinkedHashMap> results = new ArrayList<LinkedHashMap>();
        LinkedHashMap result1 = new LinkedHashMap();
        result1.put("id", "3f405f65-7c23-441f-a2da-6fff76eae14a");
        result1.put("description", "this is description");
        result1.put("exchange_methods", "this is exchange method");
        result1.put("created_time", new Date());
        result1.put("visible", true);
        result1.put("thing_name", "this is thing name");
        result1.put("origin", "this is origin");
        result1.put("category_name", "this is category name");
        results.add(result1);

        List<postelastic> convertedResult = new ArrayList<>();
        convertedResult.add(new postelastic("3f405f65-7c23-441f-a2da-6fff76eae14a",
                "this is description",
                "this is exchange method",
                new Date(),
                true,
                "this is thing name",
                "this is origin",
                "this is category name"));

        Mockito.when(restTemplate.postForObject("http://post-search-service/posts", search, List.class))
            .thenReturn(results);

        given(postService.convertToPostElasticList(results)).willReturn(convertedResult);

        Assert.assertEquals(convertedResult.size(), results.size());

    }
}
