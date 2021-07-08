package com.vls.postdeleteservice;

import com.vls.postdeleteservice.controller.PostDeleteController;
import com.vls.postdeleteservice.dto.postelastic;
import com.vls.postdeleteservice.model.Category;
import com.vls.postdeleteservice.model.Post;
import com.vls.postdeleteservice.model.Thing;
import com.vls.postdeleteservice.repository.CategoryRepository;
import com.vls.postdeleteservice.repository.PostElasticRepository;
import com.vls.postdeleteservice.repository.PostRepository;
import com.vls.postdeleteservice.repository.ThingRepository;
import com.vls.postdeleteservice.service.CategoryService;
import com.vls.postdeleteservice.service.PostElasticService;
import com.vls.postdeleteservice.service.PostService;
import com.vls.postdeleteservice.service.ThingService;
import org.checkerframework.checker.units.qual.C;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.UUID;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
public class PostDeleteControllerTest extends AbstractTest {

    private PostDeleteController postDeleteController;
    private PostService postService;
    private ThingService thingService;
    private CategoryService categoryService;
    private PostElasticService postElasticService;

    @Mock
    private PostRepository postRepository;

    @Mock
    private ThingRepository thingRepository;

    @Mock
    private PostElasticRepository postElasticRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @Override
    @Before
    public void setUp() {
        super.setUp();
        postService = new PostService(postRepository);
        thingService = new ThingService(thingRepository);
        categoryService = new CategoryService(categoryRepository);
        postElasticService = new PostElasticService(postElasticRepository, thingService, categoryService);
        postDeleteController = new PostDeleteController(postService, thingService, postElasticService);
    }


    @Test
    public void deletePost() {
        UUID thingId = UUID.randomUUID();

        Post post = new Post(UUID.randomUUID(), "description", new Date(), thingId,
                true, false, "Mở", "Free", "contact");
        Thing thing = new Thing(thingId, "name", "sad", 1000, 1,
                "used time", "image", UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID());
        Thing updatedThing = new Thing(thingId, "name", "sad", 1000, 1,
                "used time", "image", UUID.randomUUID(), UUID.randomUUID(), null);
        Category category = new Category(UUID.randomUUID(), "name");
        postelastic postelastic = new postelastic();

        Mockito.when(postRepository.findById(post.getId())).thenReturn(java.util.Optional.of(post));
        Mockito.when(thingRepository.save(thing)).thenReturn(updatedThing);
        Mockito.doNothing().when(postRepository).delete(post);
        Mockito.doNothing().when(postElasticRepository).delete(postelastic);

        Mockito.when(thingRepository.findThingById(thingId)).thenReturn(thing);
        Mockito.when(categoryRepository.findCategoryById(thing.getCategory_id())).thenReturn(category);

        ResponseEntity<Boolean> response = postDeleteController.delete(post.getId());
        Assert.assertEquals(200, response.getStatusCodeValue());
        Assert.assertEquals(true, response.getBody());

        post.setStatus("Đóng");
        ResponseEntity<Boolean> responseForbidden = postDeleteController.delete(post.getId());
        Assert.assertEquals(403, responseForbidden.getStatusCodeValue());

        Mockito.when(postRepository.findById(post.getId())).thenReturn(java.util.Optional.of(new Post()));
        ResponseEntity<Boolean> responseNotFound = postDeleteController.delete(UUID.randomUUID());
        Assert.assertEquals(404, responseNotFound.getStatusCodeValue());
    }
}
