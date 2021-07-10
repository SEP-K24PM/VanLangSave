package com.vls.postservice;

import com.vls.postservice.controller.PostController;
import com.vls.postservice.model.Post;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
public class PostControllerTest extends AbstractTest{

    @InjectMocks
    private PostController postController;

    @Mock
    private RestTemplate restTemplate;

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void savePost() {
        Post post = new Post("description", new Date(), UUID.randomUUID(),
                true, false, "Mở", "Free", "contact");

        Post savedPost = new Post(UUID.randomUUID(),"description", new Date(), UUID.randomUUID(),
                true, false, "Mở", "Free", "contact");

        Mockito.when(restTemplate.postForObject("http://post-save-service/post/", post, Post.class))
                .thenReturn(savedPost);

        ResponseEntity<Post> response = postController.post(post);
        Assert.assertEquals(201, response.getStatusCodeValue());
        Assert.assertEquals(savedPost, response.getBody());
    }

    @Test
    public void updatePost() {
        UUID postId = UUID.randomUUID();
        Post post = new Post(postId,"description", new Date(), UUID.randomUUID(),
                true, false, "Mở", "Free", "contact");

        Post updatedPost = new Post(postId,"description", new Date(), UUID.randomUUID(),
                true, false, "Mở", "Free", "contact");

        Mockito.when(restTemplate.postForObject("http://post-update-service/post/", post, Post.class))
                .thenReturn(updatedPost);

        ResponseEntity<Post> response = postController.update(postId.toString(), post);
        Assert.assertEquals(200, response.getStatusCodeValue());
        Assert.assertEquals(updatedPost, response.getBody());
    }

    @Test
    public void deletePost() {
        UUID postId = UUID.randomUUID();
        Mockito.when(restTemplate.postForObject("http://post-delete-service/", postId, Boolean.class))
                .thenReturn(true);
        ResponseEntity<Boolean> response = postController.delete(postId);
        Assert.assertEquals(200, response.getStatusCodeValue());
        Assert.assertEquals(true, response.getBody());
    }
}
