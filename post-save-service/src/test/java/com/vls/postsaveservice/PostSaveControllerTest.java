package com.vls.postsaveservice;

import com.vls.postsaveservice.controller.PostSaveController;
import com.vls.postsaveservice.model.Category;
import com.vls.postsaveservice.model.Post;
import com.vls.postsaveservice.model.Thing;
import com.vls.postsaveservice.repository.CategoryRepository;
import com.vls.postsaveservice.repository.PostRepository;
import com.vls.postsaveservice.repository.ThingRepository;
import com.vls.postsaveservice.service.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import DTO.PostDTO;

import java.util.Date;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
public class PostSaveControllerTest extends AbstractTest {
    private PostSaveController postSaveController;
    private PostService postService;
    private ThingService thingService;
    private CategoryService categoryService;
    private RabbitMQSender rabbitMQSender;
    private ModelMapper modelMapper;

    @Mock
    private ThingRepository thingRepository;
    @Mock
    private PostRepository postRepository;
    @Mock
    private CategoryRepository categoryRepository;
    @Mock
    private RabbitTemplate rabbitTemplate;

    @Override
    @Before
    public void setUp() {
        super.setUp();
        thingService = new ThingService(thingRepository);
        postService = new PostService(postRepository, modelMapper);
        categoryService = new CategoryService(categoryRepository);
        rabbitMQSender = new RabbitMQSender(rabbitTemplate);
        postSaveController = new PostSaveController(postService, rabbitMQSender, thingService, categoryService);
    }


    @Test
    public void createPost() {
        UUID thingId = UUID.randomUUID();

        Post post = new Post("description", new Date(), thingId,
                true, false, "Mở", "Free", "contact");
        Thing thing = new Thing(thingId, "name", "origin", 1000,
                1, "used_time", "image", UUID.randomUUID(),
                UUID.randomUUID());
        Category category = new Category(thing.getCategory_id(), "name");

        Post savedPost = new Post(UUID.randomUUID(),"description", new Date(), thing.getId(),
                true, false, "Mở", "Free", "contact");
        Thing updatedThing = new Thing(thing.getId(), "name", "origin", 1000,
                1, "used_time", "image", category.getId(),
                UUID.randomUUID());
        

        Mockito.when(thingRepository.findThingById(thingId)).thenReturn(thing);
        Mockito.when(postRepository.save(post)).thenReturn(savedPost);
        Mockito.when(categoryRepository.findCategoryById(thing.getCategory_id())).thenReturn(category);
        Mockito.when(thingRepository.save(thing)).thenReturn(updatedThing);

        ResponseEntity<PostDTO> response = postSaveController.createPost(post);
        Assert.assertEquals(201, response.getStatusCodeValue());
        Assert.assertEquals(savedPost, response.getBody());

        ResponseEntity<PostDTO> responseForbidden = postSaveController.createPost(post);
        Assert.assertEquals(403, responseForbidden.getStatusCodeValue());
    }
}
