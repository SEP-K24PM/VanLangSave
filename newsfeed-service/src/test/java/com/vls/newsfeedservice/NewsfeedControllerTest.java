package com.vls.newsfeedservice;

import com.vls.newsfeedservice.controller.NewsfeedController;
import com.vls.newsfeedservice.model.Category;
import com.vls.newsfeedservice.model.Post;
import com.vls.newsfeedservice.model.Thing;
import com.vls.newsfeedservice.model.UserAccount;
import com.vls.newsfeedservice.repository.PostRepository;
import com.vls.newsfeedservice.service.PostService;
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

import java.util.*;

@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
public class NewsfeedControllerTest extends AbstractTest {
    private NewsfeedController newsfeedController;
    private PostService postService;

    @Mock
    private PostRepository postRepository;

    @Override
    @Before
    public void setUp() {
        super.setUp();
        postService = new PostService(postRepository);
        newsfeedController = new NewsfeedController(postService);
    }

    @Test
    public void newsFeed() {
        Post post = new Post(UUID.randomUUID(), "description", new Date(), true, false,
                "contact", UUID.randomUUID(), "Free", "Mở");
        List<Post> posts = new ArrayList<>();
        posts.add(post);
        posts.add(post);
        posts.add(post);

        Mockito.when(postRepository.findAllNewPost()).thenReturn(posts);

        ResponseEntity<List<Post>> response1 = newsfeedController.newsFeed();
        Assert.assertEquals(200, response1.getStatusCodeValue());
        Assert.assertEquals(posts, response1.getBody());
    }

    @Test
    public void details() {
        UUID postId = UUID.randomUUID();
        Category category = new Category(UUID.randomUUID(), "name");
        UserAccount userAccount = new UserAccount(UUID.randomUUID(), "email", false);
        Thing thing = new Thing(UUID.randomUUID(), "name", "origin", 100,
                1, "used time", "image");
        thing.setCategory(category);
        thing.setUserAccount(userAccount);
        Post post = new Post(postId, "description", new Date(), true, false,
                "contact", thing.getId(), "Free", "Mở");
        post.setThing(thing);

        Mockito.when(postRepository.findById(post.getId())).thenReturn(Optional.of(post));

        ResponseEntity<Post> response = newsfeedController.postDetails(post.getId().toString());
        Assert.assertEquals(200, response.getStatusCodeValue());

        Mockito.when(postRepository.findById(post.getId())).thenReturn(Optional.empty());
        ResponseEntity<Post> responseNotFound = newsfeedController.postDetails(post.getId().toString());
        Assert.assertEquals(404, responseNotFound.getStatusCodeValue());
    }
}




