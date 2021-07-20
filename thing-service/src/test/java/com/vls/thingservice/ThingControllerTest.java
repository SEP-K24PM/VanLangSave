package com.vls.thingservice;

import com.vls.thingservice.controller.ThingController;
import com.vls.thingservice.model.Category;
import com.vls.thingservice.model.Post;
import com.vls.thingservice.model.Thing;
import com.vls.thingservice.repository.CategoryRepository;
import com.vls.thingservice.repository.PostRepository;
import com.vls.thingservice.repository.ThingRepository;
import com.vls.thingservice.service.CategoryService;
import com.vls.thingservice.service.PostService;
import com.vls.thingservice.service.ThingService;
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
public class ThingControllerTest extends AbstractTest {

    private ThingController thingController;
    private ThingService thingService;
    private CategoryService categoryService;
    private PostService postService;

    @Mock
    private ThingRepository thingRepository;

    @Mock
    private PostRepository postRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @Override
    @Before
    public void setUp() {
        super.setUp();
        postService = new PostService(postRepository);
        categoryService = new CategoryService(categoryRepository);
        thingService = new ThingService(thingRepository, postService, categoryService);
        // thingController = new ThingController(thingService);
    }

    private List<Thing> createListThing(UUID userId, UUID categoryId) {

        List<Thing> results = new ArrayList<>();
        results.add(new Thing(UUID.randomUUID(),"thing name 1", "origin 1", 10000, 1,
                "used time 1", "image1.png",
                userId));
        results.add(new Thing(UUID.randomUUID(),"thing name 2", "origin 2", 10000, 1,
                "used time 2", "image2.png",
                userId));
        return results;
    }

    @Test
    public void getListThingShouldReturnRightList() {
        UUID userId = UUID.randomUUID();
        Category category = new Category(UUID.randomUUID(), "name");
        List<Thing> results = createListThing(userId, category.getId());
        List<Thing> listWithCateName = new ArrayList<>();
        listWithCateName.addAll(results);
        

        Mockito.when(thingRepository.findByUserid(userId)).thenReturn(results);
        Mockito.when(categoryRepository.existsById(category.getId())).thenReturn(true);
        Mockito.when(categoryRepository.getOne(category.getId())).thenReturn(category);

        ResponseEntity<List<Thing>> response = thingController.getAllThings(userId.toString());
        Assert.assertEquals(listWithCateName.size(), response.getBody().size());
        Assert.assertEquals(listWithCateName, response.getBody());
        Assert.assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    public void getDetails() {
        UUID thingId = UUID.randomUUID();
        Thing thing = new Thing(UUID.randomUUID(),"thing name 1", "origin 1", 10000, 1,
                "used time 1", "image1.png",
                thingId);

        Mockito.when(thingRepository.findById(thingId)).thenReturn(java.util.Optional.of(thing));

        // ResponseEntity<Thing> response = thingController.getThingDetails(thingId.toString());
        // Assert.assertEquals(thing, response.getBody());
        // Assert.assertEquals(200, response.getStatusCodeValue());

        // ResponseEntity<Thing> exceptionResponse = thingController.getThingDetails(UUID.randomUUID().toString());
        // Assert.assertEquals(404, exceptionResponse.getStatusCodeValue());
    }

    @Test
    public void addThingShouldReturnRightThing() {
        Thing newThing = new Thing("thing name 1", "origin 1", 10000, 1,
                "used time 1", "image1.png",
                UUID.randomUUID());
        Thing savedThing = new Thing(UUID.randomUUID(), newThing.getThing_name(), newThing.getOrigin(),
                newThing.getPrice(), newThing.getQuantity(), newThing.getUsed_time(), newThing.getImage(),
                newThing.getUserid());

        Mockito.when(thingRepository.save(newThing)).thenReturn(savedThing);

        ResponseEntity<Thing> result = thingController.addThing(newThing);
        Assert.assertEquals(savedThing, result.getBody());
        Assert.assertEquals(201, result.getStatusCodeValue());
    }

    @Test
    public void updateThing() {
        UUID thingId = UUID.randomUUID();
        Thing thing = new Thing("thing name 1", "origin 1", 10000, 1,
                "used time 1", "image1.png",
                UUID.randomUUID());
        Thing updatedThing = new Thing("thing name 2", "origin 1", 10000, 1,
                "used time 1", "image1.png",
                thing.getUserid());
        Post post = new Post(UUID.randomUUID(), "description", new Date(), thingId, "Mở",
                "Free", "contact", true, false);

        Mockito.when(thingRepository.findById(thingId)).thenReturn(java.util.Optional.of(thing));
        //Mockito.when(postRepository.findById(thing.getPost_id())).thenReturn(java.util.Optional.of(post));
        Mockito.when(thingRepository.save(thing)).thenReturn(updatedThing);

        ResponseEntity<Thing> response = thingController.updateThing(thingId.toString(), thing);
        Assert.assertEquals(200, response.getStatusCodeValue());
        Assert.assertEquals(updatedThing, response.getBody());

        ResponseEntity<Thing> responseNotFound = thingController.updateThing(UUID.randomUUID().toString(), thing);
        Assert.assertEquals(404, responseNotFound.getStatusCodeValue());

        post.setStatus("Đóng");
        ResponseEntity<Thing> responseForbidden = thingController.updateThing(thingId.toString(), thing);
        Assert.assertEquals(403, responseForbidden.getStatusCodeValue());

        Optional<Post> emptyPost = Optional.empty();
        Mockito.when(thingRepository.findById(thingId)).thenReturn(java.util.Optional.of(thing));
        //Mockito.when(postRepository.findById(thing.getPost_id())).thenReturn(emptyPost);
        ResponseEntity<Thing> responseRightWithNullPost = thingController.updateThing(thingId.toString(), thing);
        Assert.assertEquals(200, responseRightWithNullPost.getStatusCodeValue());
        Assert.assertEquals(updatedThing, responseRightWithNullPost.getBody());
    }

    @Test
    public void updateThingWithNullPostId() {
        UUID thingId = UUID.randomUUID();
        Thing thing = new Thing("thing name 1", "origin 1", 10000, 1,
                "used time 1", "image1.png",
                UUID.randomUUID());
        //thing.setPost_id(null);

        Thing updatedThing = new Thing("thing name 2", "origin 1", 10000, 1,
                "used time 1", "image1.png",
                thing.getUserid());
        //updatedThing.setPost_id(null);

        Mockito.when(thingRepository.findById(thingId)).thenReturn(java.util.Optional.of(thing));
        Mockito.when(thingRepository.save(thing)).thenReturn(updatedThing);

        ResponseEntity<Thing> responseRightWithNullPostID = thingController.updateThing(
                thingId.toString(), thing);
        Assert.assertEquals(200, responseRightWithNullPostID.getStatusCodeValue());
        Assert.assertEquals(updatedThing, responseRightWithNullPostID.getBody());
    }

    @Test
    public void deleteThing() {
        UUID thingId = UUID.randomUUID();
        Thing thing = new Thing("thing name 1", "origin 1", 10000, 1,
                "used time 1", "image1.png",
                UUID.randomUUID());

        Post post = new Post(UUID.randomUUID(), "description", new Date(), thingId, "Mở",
                "Free", "contact", true, false);

        Mockito.when(thingRepository.findById(thingId)).thenReturn(java.util.Optional.of(thing));
        //Mockito.when(postRepository.findById(thing.getPost_id())).thenReturn(java.util.Optional.of(post));
        Mockito.doNothing().when(thingRepository).deleteById(thingId);

        ResponseEntity<Thing> response = thingController.deleteThing(thingId.toString());
        Assert.assertEquals(204, response.getStatusCodeValue());

        ResponseEntity<Thing> exceptionResponse = thingController.deleteThing(UUID.randomUUID().toString());
        Assert.assertEquals(404, exceptionResponse.getStatusCodeValue());
    }

    @Test
    public void deleteThingWithNullPostId() {
        UUID thingId = UUID.randomUUID();
        Thing thing = new Thing("thing name 1", "origin 1", 10000, 1,
                "used time 1", "image1.png",
                UUID.randomUUID());
        //thing.setPost_id(null);

        Mockito.when(thingRepository.findById(thingId)).thenReturn(java.util.Optional.of(thing));
        Mockito.doNothing().when(thingRepository).deleteById(thingId);

        ResponseEntity<Thing> responseRightWithNullPostID = thingController.deleteThing(thingId.toString());
        Assert.assertEquals(204, responseRightWithNullPostID.getStatusCodeValue());
    }

    @Test
    public void deleteThingWithInvalidPostStatus() {
        UUID thingId = UUID.randomUUID();
        Thing thing = new Thing("thing name 1", "origin 1", 10000, 1,
                "used time 1", "image1.png",
                UUID.randomUUID());
        Post post = new Post(UUID.randomUUID(), "description",
                new Date(), UUID.randomUUID(), "Đóng","Free", "contact", true, false);

        //thing.setPost_id(post.getThing_id());

        Mockito.when(thingRepository.findById(thingId)).thenReturn(java.util.Optional.of(thing));
        //Mockito.when(postRepository.findById(thing.getPost_id())).thenReturn(java.util.Optional.of(post));
        Mockito.doNothing().when(thingRepository).deleteById(thingId);

        ResponseEntity<Thing> response = thingController.deleteThing(thingId.toString());
        Assert.assertEquals(403, response.getStatusCodeValue());
    }

}
