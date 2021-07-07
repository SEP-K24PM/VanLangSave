package com.vls.postsaveservice;

import com.vls.postsaveservice.controller.PostSaveController;
import com.vls.postsaveservice.dto.postelastic;
import com.vls.postsaveservice.model.Post;
import com.vls.postsaveservice.model.Thing;
import com.vls.postsaveservice.repository.PostRepository;
import com.vls.postsaveservice.repository.ThingRepository;
import com.vls.postsaveservice.service.PostService;
import com.vls.postsaveservice.service.RabbitMQSender;
import com.vls.postsaveservice.service.ThingService;
import org.checkerframework.checker.units.qual.A;
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

import java.util.Date;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
public class PostSaveControllerTest extends AbstractTest {
    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @InjectMocks
    private PostSaveController postSaveController;

    @Mock
    private PostService postService;

    @Mock
    private ThingService thingService;


    @Mock
    RabbitMQSender rabbitMQSender;


    @Test
    public void createPost() {
        UUID thingId = UUID.randomUUID();

        Post post = new Post("description", new Date(), thingId,
                true, false, "Mở", "Free", "contact");

        Post savedPost = new Post(UUID.randomUUID(),"description", new Date(), UUID.randomUUID(),
                true, false, "Mở", "Free", "contact");

        postelastic postelastic = new postelastic();

        Mockito.when(postService.checkThingIsAvailable(thingId)).thenReturn(true);
        Mockito.when(postService.createPost(post)).thenReturn(savedPost);
        Mockito.when(rabbitMQSender.convertToPostElastic(savedPost)).thenReturn(postelastic);
        Mockito.when(thingService.updateThingWithNewPost(savedPost.getThing_id(), savedPost.getId())).thenReturn(null);
        Thing thing = new Thing("name", "origin", 1000, 1, "used time", "image", UUID.randomUUID(),
                UUID.randomUUID(), null);

        ResponseEntity<Post> response = postSaveController.createPost(post);
        Assert.assertEquals(201, response.getStatusCodeValue());
        Assert.assertEquals(savedPost, response.getBody());

        Mockito.when(postService.checkThingIsAvailable(thingId)).thenReturn(false);
        ResponseEntity<Post> responseForbidden = postSaveController.createPost(post);
        Assert.assertEquals(403, responseForbidden.getStatusCodeValue());
    }
}
