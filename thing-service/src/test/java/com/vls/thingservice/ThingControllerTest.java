package com.vls.thingservice;

import DTO.ThingDTO;
import com.vls.thingservice.controller.ThingController;
import com.vls.thingservice.model.Category;
import com.vls.thingservice.model.Post;
import com.vls.thingservice.model.Thing;
import com.vls.thingservice.model.ThingForSaving;
import com.vls.thingservice.repository.CategoryRepository;
import com.vls.thingservice.repository.PostRepository;
import com.vls.thingservice.repository.ThingFSRepository;
import com.vls.thingservice.repository.ThingRepository;
import com.vls.thingservice.service.CategoryService;
import com.vls.thingservice.service.PostService;
import com.vls.thingservice.service.ThingService;
import org.checkerframework.checker.units.qual.C;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
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
    private ModelMapper modelMapper;

    @Mock
    private ThingRepository thingRepository;
    @Mock
    private ThingFSRepository thingFSRepository;
    @Mock
    private PostRepository postRepository;
    @Mock
    private CategoryRepository categoryRepository;

    @Override
    @Before
    public void setUp() {
        super.setUp();
        modelMapper = new ModelMapper();
        postService = new PostService(postRepository);
        categoryService = new CategoryService(categoryRepository);
        thingService = new ThingService(thingRepository, thingFSRepository);
        thingController = new ThingController(thingService, modelMapper, postService, categoryService);
    }

    private Thing getThing() {
        return new Thing(UUID.randomUUID(),"thing name 1", "origin 1", 10000, 1,
                "used time 1", "image1.png", UUID.randomUUID());
    }

    private List<Thing> createListThing(UUID userId, UUID categoryId) {
        List<Thing> results = new ArrayList<>();
        Thing thing = getThing();
        thing.setUserid(userId);
        thing.setCategory(new Category(categoryId, "name"));
        results.add(thing);
        results.add(thing);
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
        ThingDTO thingDTO = modelMapper.map(thing, ThingDTO.class);
        Mockito.when(thingRepository.findById(thingId)).thenReturn(java.util.Optional.of(thing));

         ResponseEntity<ThingDTO> response = thingController.getThingDetails(thingId.toString());
         Assert.assertEquals(200, response.getStatusCodeValue());

         ResponseEntity<ThingDTO> exceptionResponse = thingController.getThingDetails(UUID.randomUUID().toString());
         Assert.assertEquals(404, exceptionResponse.getStatusCodeValue());
    }

    @Test
    public void addThingShouldReturnRightThing() {
        Thing newThing = new Thing("thing name 1", "origin 1", 10000, 1,
                "used time 1", "image1.png",
                UUID.randomUUID());
        ThingDTO thingDTO = modelMapper.map(newThing, ThingDTO.class);
        ThingForSaving thing = modelMapper.map(thingDTO, ThingForSaving.class);

        Mockito.when(thingFSRepository.save(thing)).thenReturn(thing);

        ResponseEntity<ThingForSaving> result = thingController.addThing(thingDTO);
        Assert.assertEquals(201, result.getStatusCodeValue());
    }

    @Test
    public void updateThing() {
        UUID thingId = UUID.randomUUID();
        ThingForSaving thing = new ThingForSaving(thingId,"thing name 1", "origin", 1000, 1,
                "used time 1", "image1.png", UUID.randomUUID(), UUID.randomUUID());
        ThingDTO thingDTO = new ThingDTO(thingId, thing.getThing_name(), thing.getOrigin(), thing.getPrice(),
                thing.getPrice(), thing.getUsed_time(), thing.getImage(), thing.getUser_id(), thing.getCategory_id());
        Post post = new Post(UUID.randomUUID(), "description", new Date(), thingId, "Mở",
                "Free", "contact", true, false);

        Mockito.when(thingFSRepository.findById(thingId)).thenReturn(java.util.Optional.of(thing));
        Mockito.when(thingFSRepository.getOne(thingId)).thenReturn(thing);
        Mockito.when(thingFSRepository.save(thing)).thenReturn(thing);

        ResponseEntity<ThingForSaving> response = thingController.updateThing(thingId.toString(), thingDTO);
        Assert.assertEquals(200, response.getStatusCodeValue());
        Assert.assertEquals(thing, response.getBody());

        Mockito.when(postRepository.findByThingId(thingId)).thenReturn(post);
        ResponseEntity<ThingForSaving> forbiddenResponse = thingController.updateThing(thingId.toString(), thingDTO);
        Assert.assertEquals(403, forbiddenResponse.getStatusCodeValue());

        Mockito.when(thingFSRepository.findById(thingId)).thenReturn(Optional.empty());
        ResponseEntity<ThingForSaving> responseNotFound = thingController.updateThing(thingId.toString(), thingDTO);
        Assert.assertEquals(404, responseNotFound.getStatusCodeValue());
    }

    @Test
    public void deleteThing() {
        UUID thingId = UUID.randomUUID();
        ThingForSaving thing = new ThingForSaving(thingId,"thing name 1", "origin", 1000, 1,
                "used time 1", "image1.png", UUID.randomUUID(), UUID.randomUUID());

        Post post = new Post(UUID.randomUUID(), "description", new Date(), thingId, "Mở",
                "Free", "contact", true, false);

        Mockito.when(thingFSRepository.findById(thingId)).thenReturn(java.util.Optional.of(thing));
        Mockito.doNothing().when(thingFSRepository).deleteById(thingId);

        ResponseEntity<Thing> response = thingController.deleteThing(thingId.toString());
        Assert.assertEquals(204, response.getStatusCodeValue());

        Mockito.when(postRepository.findByThingId(thingId)).thenReturn(post);
        ResponseEntity<Thing> forbiddenResponse = thingController.deleteThing(thingId.toString());
        Assert.assertEquals(403, forbiddenResponse.getStatusCodeValue());

        ResponseEntity<Thing> exceptionResponse = thingController.deleteThing(UUID.randomUUID().toString());
        Assert.assertEquals(404, exceptionResponse.getStatusCodeValue());
    }

    @Test
    public void getListAvaialbeTest() {
        UUID userId = UUID.randomUUID();
        List<Thing> things = new ArrayList<>();
        things.add(getThing());
        things.add(getThing());

        Mockito.when(thingRepository.findAvailableThing(userId)).thenReturn(things);

        ResponseEntity<List<Thing>> response = thingController.getListAvaialbe(userId.toString());
        Assert.assertEquals(200, response.getStatusCodeValue());
        Assert.assertEquals(things.size(), response.getBody().size());
    }

    @Test
    public void getPostByThingIdTest() {
        Post post = new Post(UUID.randomUUID(), "description", new Date(), UUID.randomUUID(), "Mở",
                "exchange_method",
                "contact", true, false);
        Mockito.when(postRepository.findByThingId(post.getThing_id())).thenReturn(post);

        ResponseEntity<Post> response = thingController.getPostByThingId(post.getThing_id().toString());
        Assert.assertEquals(200, response.getStatusCodeValue());

        post.setVisible(false);
        post.setDeletion(true);
        ResponseEntity<Post> notFoundResponse = thingController.getPostByThingId(post.getThing_id().toString());
        Assert.assertEquals(404, notFoundResponse.getStatusCodeValue());

        Mockito.when(postRepository.findByThingId(post.getThing_id())).thenReturn(null);
        ResponseEntity<Post> noPostResponse = thingController.getPostByThingId(post.getThing_id().toString());
        Assert.assertEquals(404, noPostResponse.getStatusCodeValue());
    }

    @Test
    public void getCategoriesTest() {
        Category category = new Category(UUID.randomUUID(), "name");
        List<Category> list = new ArrayList<>();
        list.add(category);
        list.add(category);

        Mockito.when(categoryRepository.findAll()).thenReturn(list);

        ResponseEntity<List<Category>> response = thingController.getCategories();
        Assert.assertEquals(200, response.getStatusCodeValue());
        Assert.assertEquals(list.size(), response.getBody().size());
    }
}
