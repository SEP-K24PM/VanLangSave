package com.vls.thingservice;

import com.vls.thingservice.controller.ThingController;
import com.vls.thingservice.model.Post;
import com.vls.thingservice.model.Thing;
import com.vls.thingservice.repository.CategoryRepository;
import com.vls.thingservice.repository.ThingRepository;
import com.vls.thingservice.service.CategoryService;
import com.vls.thingservice.service.PostService;
import com.vls.thingservice.service.ThingService;
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
public class ThingControllerTest extends AbstractTest {

    @InjectMocks
    private ThingController thingController;

    @Mock
    private ThingService thingService;

    @Mock
    private CategoryService categoryService;

    @Mock
    private PostService postService;

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    private List<Thing> createListThing(UUID userId) {

        List<Thing> results = new ArrayList<>();
        results.add(new Thing(UUID.randomUUID(),"thing name 1", "origin 1", 10000, 1,
                "used time 1", "image1.png",
                userId, UUID.randomUUID(), UUID.randomUUID()));
        results.add(new Thing(UUID.randomUUID(),"thing name 2", "origin 2", 10000, 1,
                "used time 2", "image2.png",
                userId, UUID.randomUUID(), UUID.randomUUID()));
        return results;
    }

    @Test
    public void getListThingShouldReturnRightList() {
        UUID userId = UUID.randomUUID();
        List<Thing> results = createListThing(userId);
        List<Thing> listWithCateName = new ArrayList<>();
        listWithCateName.addAll(results);
        for (Thing t: listWithCateName) {
            t.setCategory_name("catename");
        }

        Mockito.when(thingService.getListThings(userId)).thenReturn(results);
        Mockito.when(categoryService.addCategoryNameToThing(results)).thenReturn(listWithCateName);

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
                thingId, UUID.randomUUID(), UUID.randomUUID());

        Mockito.when(thingService.getThingDetails(thingId.toString())).thenReturn(java.util.Optional.of(thing));

        ResponseEntity<Thing> response = thingController.getThingDetails(thingId.toString());
        Assert.assertEquals(thing, response.getBody());
        Assert.assertEquals(200, response.getStatusCodeValue());

        ResponseEntity<Thing> exceptionResponse = thingController.getThingDetails(UUID.randomUUID().toString());
        Assert.assertEquals(404, exceptionResponse.getStatusCodeValue());
    }

    @Test
    public void addThingShouldReturnRightThing() {
        Thing newThing = new Thing("thing name 1", "origin 1", 10000, 1,
                "used time 1", "image1.png",
                UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID());
        Thing savedThing = new Thing(UUID.randomUUID(), newThing.getThing_name(), newThing.getOrigin(),
                newThing.getPrice(), newThing.getQuantity(), newThing.getUsed_time(), newThing.getImage(),
                newThing.getUserid(), newThing.getCategory_id(), newThing.getPost_id());

        Mockito.when(thingService.addThing(newThing)).thenReturn(savedThing);

        ResponseEntity<Thing> result = thingController.addThing(newThing);
        Assert.assertEquals(savedThing, result.getBody());
        Assert.assertEquals(201, result.getStatusCodeValue());
    }

    @Test
    public void updateThing() {
        String thingId = UUID.randomUUID().toString();
        Thing thing = new Thing("thing name 1", "origin 1", 10000, 1,
                "used time 1", "image1.png",
                UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID());

        Thing updatedThing = new Thing("thing name 2", "origin 1", 10000, 1,
                "used time 1", "image1.png",
                thing.getUserid(), UUID.randomUUID(), UUID.randomUUID());

        Mockito.when(thingService.getThingDetails(thingId)).thenReturn(java.util.Optional.of(thing));
        Mockito.when(thingService.updateThing(thing)).thenReturn(updatedThing);
        Mockito.when(thingService.checkIsPosibleToUpdate(thing)).thenReturn(true);

        ResponseEntity<Thing> response = thingController.updateThing(thingId, thing);
        Assert.assertEquals(200, response.getStatusCodeValue());
        Assert.assertEquals(updatedThing, response.getBody());

        ResponseEntity<Thing> exceptionResponse = thingController.updateThing(UUID.randomUUID().toString(), thing);
        Assert.assertEquals(404, exceptionResponse.getStatusCodeValue());

    }

    @Test
    public void updateThingWithInvalidPostStatus() {
        String thingId = UUID.randomUUID().toString();
        Thing thing = new Thing("thing name 1", "origin 1", 10000, 1,
                "used time 1", "image1.png",
                UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID());
        Post post = new Post(UUID.randomUUID(), "description",
                new Date(), UUID.randomUUID(), "Đóng","Free", "contact");

        thing.setPost_id(post.getThing_id());

        Mockito.when(postService.getPost(post.getId())).thenReturn(java.util.Optional.of(post));
        Mockito.when(thingService.getThingDetails(thingId)).thenReturn(java.util.Optional.of(thing));

        ResponseEntity<Thing> response = thingController.updateThing(thingId, thing);
        Assert.assertEquals(403, response.getStatusCodeValue());
    }

    @Test
    public void deleteThing() {
        String thingId = UUID.randomUUID().toString();
        Thing thing = new Thing("thing name 1", "origin 1", 10000, 1,
                "used time 1", "image1.png",
                UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID());

        Thing updatedThing = new Thing("thing name 2", "origin 1", 10000, 1,
                "used time 1", "image1.png",
                thing.getUserid(), UUID.randomUUID(), UUID.randomUUID());

        Mockito.when(thingService.getThingDetails(thingId)).thenReturn(java.util.Optional.of(thing));
        Mockito.when(thingService.deleteThing(thingId)).thenReturn(true);
        Mockito.when(thingService.checkIsPosibleToUpdate(thing)).thenReturn(true);

        ResponseEntity<Thing> response = thingController.deleteThing(thingId);
        Assert.assertEquals(204, response.getStatusCodeValue());

        ResponseEntity<Thing> exceptionResponse = thingController.deleteThing(UUID.randomUUID().toString());
        Assert.assertEquals(404, exceptionResponse.getStatusCodeValue());

    }

    @Test
    public void deleteThingWithInvalidPostStatus() {
        String thingId = UUID.randomUUID().toString();
        Thing thing = new Thing("thing name 1", "origin 1", 10000, 1,
                "used time 1", "image1.png",
                UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID());
        Post post = new Post(UUID.randomUUID(), "description",
                new Date(), UUID.randomUUID(), "Đóng","Free", "contact");

        thing.setPost_id(post.getThing_id());

        Mockito.when(postService.getPost(post.getId())).thenReturn(java.util.Optional.of(post));
        Mockito.when(thingService.getThingDetails(thingId)).thenReturn(java.util.Optional.of(thing));

        ResponseEntity<Thing> response = thingController.deleteThing(thingId);
        Assert.assertEquals(403, response.getStatusCodeValue());
    }

}
