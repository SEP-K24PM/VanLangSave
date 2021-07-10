package com.vls.searchservice;

import com.vls.searchservice.controller.SearchController;
import com.vls.searchservice.dto.postelastic;
import com.vls.searchservice.service.PostService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
public class SearchControllerTest extends AbstractTest {
    private SearchController searchController;

    @Mock
    private RestTemplate restTemplate;

    @Override
    @Before
    public void setUp() {
        super.setUp();
        searchController = new SearchController(restTemplate);
    }

    @Test
    public void testPostSearch() {
        String search = "search";

        List<postelastic> result = new ArrayList<>();
        result.add(new postelastic("3f405f65-7c23-441f-a2da-6fff76eae14a",
                "this is description",
                "this is exchange method",
                new Date(),
                true,
                "this is thing name",
                "this is origin",
                "this is category name"));

       Mockito.when(restTemplate.postForObject("http://post-search-service/posts", search, List.class)).thenReturn(result);
       ResponseEntity<List<postelastic>> response = searchController.posts(search);
       Assert.assertEquals(200, response.getStatusCodeValue());
       Assert.assertEquals(result, response.getBody());
    }
}
