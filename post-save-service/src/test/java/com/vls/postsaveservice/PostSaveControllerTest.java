package com.vls.postsaveservice;

import Constants.ActionConstants;
import DTO.CategoryDTO;
import DTO.PostElastic;
import DTO.ThingDTO;
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
import java.util.Optional;
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
        modelMapper = new ModelMapper();
        postService = new PostService(postRepository, modelMapper);
        categoryService = new CategoryService(categoryRepository);
        rabbitMQSender = new RabbitMQSender(rabbitTemplate);
        postSaveController = new PostSaveController(postService, rabbitMQSender, thingService, categoryService);
    }

    private Post getPost() {
        return new Post(UUID.randomUUID(), "description", new Date(), UUID.randomUUID(),
                true, false, "Mở", "Free", "contact", UUID.randomUUID(), UUID.randomUUID());
    }

    private Thing getThing() {
        return new Thing(UUID.randomUUID(), "name", "origin",
                1000, 1, "used_time", "image",
                UUID.randomUUID(), UUID.randomUUID());
    }

    @Test
    public void createPostTest() {
        UUID thingId = UUID.randomUUID();
        Post post = getPost();
        post.setThing_id(thingId);
        Thing thing = getThing();
        thing.setId(thingId);
        Category category = new Category(thing.getCategory_id(), "name");
        PostElastic postElastic = new PostElastic(post.getId().toString(), post.getDescription(), post.getExchange_method(),
                post.getCreated_time(), post.isVisible(), thing.getThing_name(),
                thing.getOrigin(), category.getCategory_name(), thing.getImage());

        Mockito.when(postRepository.save(post)).thenReturn(post);
        Mockito.when(thingRepository.findThingById(thingId)).thenReturn(thing);
        Mockito.when(categoryRepository.findCategoryById(thing.getCategory_id())).thenReturn(category);
        Mockito.when(thingRepository.save(thing)).thenReturn(thing);
        Mockito.doNothing().when(rabbitTemplate).convertAndSend("exchange", "routing", postElastic);

        ResponseEntity<PostDTO> response = postSaveController.createPost(post);
        Assert.assertEquals(201, response.getStatusCodeValue());
    }

    @Test
    public void updatePostTest() {
        Post post = getPost();
        Thing thing = getThing();
        thing.setId(post.getThing_id());
        Category category = new Category(thing.getCategory_id(), "name");
        PostElastic postElastic = new PostElastic(post.getId().toString(), post.getDescription(), post.getExchange_method(),
                post.getCreated_time(), post.isVisible(), thing.getThing_name(),
                thing.getOrigin(), category.getCategory_name(), thing.getImage());

        Mockito.when(postRepository.findById(post.getId())).thenReturn(Optional.of(post));
        Mockito.when(postRepository.save(post)).thenReturn(post);
        Mockito.when(thingRepository.findThingById(thing.getId())).thenReturn(thing);
        Mockito.when(categoryRepository.findCategoryById(thing.getCategory_id())).thenReturn(category);
        Mockito.doNothing().when(rabbitTemplate).convertAndSend("exchange", "routing", postElastic);

        ResponseEntity<PostDTO> response = postSaveController.updatePost(post.getId().toString(), post);
        Assert.assertEquals(200, response.getStatusCodeValue());

        post.setStatus("Đóng");
        ResponseEntity<PostDTO> forbiddenResponse = postSaveController.updatePost(post.getId().toString(), post);
        Assert.assertEquals(403, forbiddenResponse.getStatusCodeValue());

        Mockito.when(postRepository.findById(post.getId())).thenReturn(Optional.empty());
        ResponseEntity<PostDTO> notFoundResponse = postSaveController.updatePost(post.getId().toString(), post);
        Assert.assertEquals(404, notFoundResponse.getStatusCodeValue());
    }

    @Test
    public void deletePostTest() {
        Post post = getPost();
        Thing thing = getThing();
        thing.setId(post.getThing_id());
        Category category = new Category(thing.getCategory_id(), "name");
        PostElastic postElastic = new PostElastic(post.getId().toString(), post.getDescription(), post.getExchange_method(),
                post.getCreated_time(), post.isVisible(), thing.getThing_name(),
                thing.getOrigin(), category.getCategory_name(), thing.getImage());

        Mockito.when(postRepository.findById(post.getId())).thenReturn(Optional.of(post));
        Mockito.doNothing().when(postRepository).delete(post);
        Mockito.doNothing().when(rabbitTemplate).convertAndSend("exchange", "routing", postElastic);

        ResponseEntity<Boolean> response = postSaveController.delete(post.getId());
        Assert.assertEquals(200, response.getStatusCodeValue());

        post.setStatus("Đóng");
        ResponseEntity<Boolean> forbiddenResponse = postSaveController.delete(post.getId());
        Assert.assertEquals(403, forbiddenResponse.getStatusCodeValue());

        Mockito.when(postRepository.findById(post.getId())).thenReturn(Optional.empty());
        ResponseEntity<Boolean> notFoundResponse = postSaveController.delete(post.getId());
        Assert.assertEquals(404, notFoundResponse.getStatusCodeValue());
    }

    @Test
    public void cancelPostTest() {
        Post post = getPost();
        Post cancelledPost = getPost();
        cancelledPost.setStatus(ActionConstants.PostStatus.CANCELLED);
        Mockito.when(postRepository.findById(post.getId())).thenReturn(Optional.of(post));
        Mockito.when(postRepository.save(post)).thenReturn(cancelledPost);

        ResponseEntity<Post> response = postSaveController.cancel(post.getId());
        Assert.assertEquals(200, response.getStatusCodeValue());

        Mockito.when(postRepository.findById(post.getId())).thenReturn(Optional.empty());
        ResponseEntity<Post> notFoundResponse = postSaveController.cancel(post.getId());
        Assert.assertEquals(404, notFoundResponse.getStatusCodeValue());
    }

    @Test
    public void completePostTest() {
        String given = UUID.randomUUID().toString();
        Post post = getPost();
        Thing thing = getThing();
        thing.setId(post.getThing_id());
        thing.setUser_id(UUID.fromString(given));
        post.setGiver(null);
        post.setGiven(UUID.randomUUID());

        Mockito.when(postRepository.findById(post.getId())).thenReturn(Optional.of(post));
        Mockito.when(thingRepository.findThingById(thing.getId())).thenReturn(thing);
        Mockito.when(postRepository.save(post)).thenReturn(post);

        ResponseEntity<Post> response = postSaveController.complete(post.getId().toString(), given);
        Assert.assertEquals(200, response.getStatusCodeValue());

        thing.setUser_id(UUID.randomUUID());
        post.setGiven(null);
        post.setGiver(UUID.randomUUID());
        ResponseEntity<Post> completeResponse = postSaveController.complete(post.getId().toString(), given);
        Assert.assertEquals(200, completeResponse.getStatusCodeValue());

        Mockito.when(postRepository.findById(post.getId())).thenReturn(Optional.empty());
        ResponseEntity<Post> notFoundResponse = postSaveController.complete(post.getId().toString(), given);
        Assert.assertEquals(404, notFoundResponse.getStatusCodeValue());
    }
}
