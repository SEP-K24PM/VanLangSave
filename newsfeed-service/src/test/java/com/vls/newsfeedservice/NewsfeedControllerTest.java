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

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;
import java.util.List;


@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
public class NewsfeedControllerTest extends AbstractTest {
    private NewsfeedController newsfeedController;
    private PostService postService;
    private ThingService thingService;

    @Mock
    private PostRepository postRepository;
    //private Object PostRepository;

    @Mock
    private ThingRepository thingRepository;
    //private Object ThingRepository;


    @Override
    @Before
    public void setUp() {
        super.setUp();
        postService = new PostService(postRepository, thingService);
        thingService = new ThingService(thingRepository);
        newsfeedController = new NewsfeedController(postService, thingService);
    }

    @Test
    public void newsFeed() {
        UUID thingId = UUID.randomUUID();
        Thing thing = new Thing();

        PostWithThing postWithThing = new PostWithThing(UUID.randomUUID(), "description",
                new Date(), true, false, "contact", thingId, "Free", "Mở", thing);
        List<Post> posts = new ArrayList<>();
        posts.add(new Post(UUID.randomUUID(), "description", new Date(), true, false,
                "contact", thingId, "Free", "Mở"));

        Mockito.when(postRepository.findAllNewPost()).thenReturn(posts);
        Mockito.when(thingRepository.findById(thing.getId())).thenReturn(java.util.Optional.of(thing));

        ResponseEntity<List<Post>> response1 = newsfeedController.newsFeed();
        Assert.assertEquals(200, response1.getStatusCodeValue());
        Assert.assertEquals(true, response1.getBody());

        ResponseEntity<PostWithThing> response = newsfeedController.postDetails(postWithThing.getId());
        Assert.assertEquals(200, response.getStatusCodeValue());
        Assert.assertEquals(true, response.getBody());

        Mockito.doNothing().when(postRepository).findAllNewPost();
        ResponseEntity<List<Post>> responseNotFound = newsfeedController.newsFeed();
        Assert.assertEquals(404, responseNotFound.getStatusCodeValue());

        Mockito.doNothing().when(postRepository).findById(postWithThing.getId());
        ResponseEntity<PostWithThing> responseNotFound1 = newsfeedController.postDetails(postWithThing.getId());
        Assert.assertEquals(404, responseNotFound1.getStatusCodeValue());
    }
}




