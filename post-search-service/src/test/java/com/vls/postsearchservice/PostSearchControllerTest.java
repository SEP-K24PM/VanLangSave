package com.vls.postsearchservice;

import com.vls.postsearchservice.controller.PostSearchController;
import com.vls.postsearchservice.dto.postelastic;
import com.vls.postsearchservice.repository.PostDAOImpl;
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
public class PostSearchControllerTest  extends AbstractTest{
    private PostSearchController postSearchController;
    @Mock
    private PostDAOImpl postDAOIml;

    @Override
    @Before
    public void setUp() {
        super.setUp();
        postSearchController = new PostSearchController(postDAOIml);
    }

    @Test
    public void testPostSearch() {
         List<postelastic> posts = new ArrayList<>();
         postelastic post = new postelastic(
                 "3f405f65-7c23-441f-a2da-6fff76eae14a", "description",
                 "exchange method", new Date(), true,
                 "thing name", "origin", "category name", "image");
         posts.add(post);
         posts.add(post);
         String search = "name";

         Mockito.when(postDAOIml.search(search)).thenReturn(posts);

         ResponseEntity<List<postelastic>> response = postSearchController.posts(search);
         Assert.assertEquals(200, response.getStatusCodeValue());
         Assert.assertEquals(posts, response.getBody());
    }
}
