package com.vls.searchservice;

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

       Mockito.when(restTemplate.postForObject("http://post-search-service/posts", search, List.class)).thenReturn(result);
       ResponseEntity<List<postelastic>> response = searchController.posts(search);
       Assert.assertEquals(200, response.getStatusCodeValue());
       Assert.assertEquals(result, response.getBody());
    }

    @Test
    public void newsFeed() throws ParseException {
        ThingDTO thing = new ThingDTO(UUID.randomUUID(), "name", "origin",
                10000, 1, "used time", "image.jpeg",
                UUID.randomUUID(), UUID.randomUUID());
        ThingDTO thing1 = new ThingDTO(UUID.randomUUID(), "name", "origin",
                10000, 1, "used time", "image.jpeg",
                UUID.randomUUID(), UUID.randomUUID());
        PostDTO post = new PostDTO(UUID.randomUUID(), "description", new Date(), true, false,
                "contact", "method exchange", "Mở", thing.getId());
        PostDTO post1 = new PostDTO(UUID.randomUUID(), "description",
                new SimpleDateFormat("dd/MM/yyyy").parse("31/12/1998"),
                true, false,
                "contact", "method exchange", "Mở", thing1.getId());
        List<PostDTO> list = new ArrayList<>();
        list.add(post);
        list.add(post1);

        Mockito.when(restTemplate.getForObject("http://newsfeed-service/", List.class)).thenReturn(list);
        ResponseEntity<List<PostDTO>> response = searchController.newsfeed();
        Assert.assertEquals(200, response.getStatusCodeValue());
        Assert.assertEquals(list, response.getBody());
    }

    @Test
    public void details() {
        ThingDTO thing = new ThingDTO(UUID.randomUUID(), "name", "origin",
                10000, 1, "used time", "image.jpeg",
                UUID.randomUUID(), UUID.randomUUID());
        PostDTO post = new PostDTO(UUID.randomUUID(), "description", new Date(), true, false,
                "contact", "method exchange", "Mở", thing.getId());

        Mockito.when(restTemplate.postForObject("http://newsfeed-service/post/", post.getId(), PostDTO.class)).thenReturn(post);
        ResponseEntity<PostDTO> response = searchController.postDetails(post.getId());
        Assert.assertEquals(200, response.getStatusCodeValue());
        Assert.assertEquals(post, response.getBody());
    }
}
