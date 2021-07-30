package com.vls.searchservice;

import Constants.PostSearchApiConstants;
import DTO.PostDTO;
import DTO.ThingDTO;
import com.vls.searchservice.controller.SearchController;
import com.vls.searchservice.dto.postelastic;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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

       Mockito.when(restTemplate.postForObject(PostSearchApiConstants.Post.POST_SEARCH, search, List.class)).thenReturn(result);
       ResponseEntity<List<postelastic>> response = searchController.posts(search);
       Assert.assertEquals(200, response.getStatusCodeValue());
       Assert.assertEquals(result, response.getBody());
    }

    @Test
    public void newsFeed() throws ParseException {
        PostDTO post = new PostDTO(UUID.randomUUID(), "description", new Date(), true, false,
                "contact", "method exchange", "Mở",
                UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID());
        List<PostDTO> list = new ArrayList<>();
        list.add(post);

        Mockito.when(restTemplate.getForObject(PostSearchApiConstants.Post.NEWSFEED, List.class)).thenReturn(list);
        ResponseEntity<List<PostDTO>> response = searchController.newsfeed();
        Assert.assertEquals(200, response.getStatusCodeValue());
        Assert.assertEquals(list, response.getBody());
    }

    @Test
    public void details() {
        PostDTO post = new PostDTO(UUID.randomUUID(), "description", new Date(), true, false,
                "contact", "method exchange", "Mở",
                UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID());

        Mockito.when(restTemplate.postForObject(PostSearchApiConstants.Post.POST_DETAILS, post.getId().toString(), PostDTO.class))
                .thenReturn(post);
        ResponseEntity<PostDTO> response = searchController.postDetails(post.getId().toString());
        Assert.assertEquals(200, response.getStatusCodeValue());
        Assert.assertEquals(post, response.getBody());
    }
}
