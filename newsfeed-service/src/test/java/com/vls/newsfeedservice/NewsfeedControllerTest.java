package com.vls.newsfeedservice;

import com.vls.newsfeedservice.controller.NewsfeedController;
import com.vls.newsfeedservice.dto.PostWithThing;
import com.vls.newsfeedservice.model.Post;
import com.vls.newsfeedservice.model.Thing;
import com.vls.newsfeedservice.repository.PostRepository;
import com.vls.newsfeedservice.repository.ThingRepository;
import com.vls.newsfeedservice.service.PostService;
import com.vls.newsfeedservice.service.ThingService;
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
    private ThingService thingService;

    @Mock
    private PostRepository postRepository;
    @Mock
    private ThingRepository thingRepository;

    @Override
    @Before
    public void setUp() {
        super.setUp();
        thingService = new ThingService(thingRepository);
        postService = new PostService(postRepository, thingService);
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
        Thing thing = new Thing(UUID.randomUUID(), "name", "origin", 100,
                1, "used time", "iamge", UUID.randomUUID(), UUID.randomUUID(), postId);
        Post post = new Post(postId, "description", new Date(), true, false,
                "contact", thing.getId(), "Free", "Mở");
        PostWithThing postWithThing = new PostWithThing(postId, post.getDescription(),
                post.getCreated_time(), post.getVisible(), post.getDeletion(),
                post.getContact(), thing.getId(), post.getExchange_method(), post.getStatus(), thing);

        Mockito.when(postRepository.findById(post.getId())).thenReturn(Optional.of(post));
        Mockito.when(thingRepository.findById(post.getThing_id())).thenReturn(Optional.of(thing));

        ResponseEntity<PostWithThing> response = newsfeedController.postDetails(post.getId());
        Assert.assertEquals(200, response.getStatusCodeValue());

        Mockito.when(postRepository.findById(post.getId())).thenReturn(Optional.empty());
        ResponseEntity<PostWithThing> responseNotFound = newsfeedController.postDetails(post.getId());
        Assert.assertEquals(404, responseNotFound.getStatusCodeValue());
    }
}




