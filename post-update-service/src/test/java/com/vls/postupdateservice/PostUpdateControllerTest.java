package com.vls.postupdateservice;

import com.vls.postupdateservice.controller.PostUpdateController;
import com.vls.postupdateservice.dto.postelastic;
import com.vls.postupdateservice.model.Category;
import com.vls.postupdateservice.model.Post;
import com.vls.postupdateservice.model.Thing;
import com.vls.postupdateservice.service.CategoryService;
import com.vls.postupdateservice.service.PostElasticService;
import com.vls.postupdateservice.service.PostService;
import com.vls.postupdateservice.service.ThingService;
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
public class PostUpdateControllerTest extends AbstractTest{
    @InjectMocks
    private PostUpdateController postUpdateController;

    @Mock
    private ThingService thingService;

    @Mock
    private CategoryService categoryService;

    @Mock
    private PostService postService;

    @Mock
    private PostElasticService postElasticService;

    private UUID postId;
    private Post post;
    private Post updatedPost;
    private Thing thing;
    private Category category;
    private postelastic postelastic;

    @Override
    @Before
    public void setUp() {
        super.setUp();
        postId = UUID.randomUUID();
        post = new Post(postId,"description", new Date(), UUID.randomUUID(),
                true, false, "Mở", "Free", "contact");

        updatedPost = new Post(postId,"description", new Date(), UUID.randomUUID(),
                true, false, "Mở", "Free", "contact");

        thing = new Thing("thing name 1", "origin 1", 10000, 1,
                "used time 1", "image1.png",
                UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID());

        category = new Category("name");

        postelastic = new postelastic(post.getDescription(), post.getExchange_method(), post.getCreated_time(),
                post.isVisible(), thing.getThing_name(), thing.getOrigin(), category.getCategory_name());
    }

    @Test
    public void updateReturnOK() {
        Mockito.when(postService.getPostDetails(postId)).thenReturn(java.util.Optional.of(post));
        Mockito.when(postService.checkIfAllowUpdate(post)).thenReturn(true);
        Mockito.when(postService.updatePost(post)).thenReturn(updatedPost);
        Mockito.when(postElasticService.update(post)).thenReturn(postelastic);

        ResponseEntity<Post> response = postUpdateController.update(post);
        Assert.assertEquals(200, response.getStatusCodeValue());
        Assert.assertEquals(updatedPost, response.getBody());
    }

    @Test
    public void updateReturnNotFound() {
        Mockito.when(postService.checkIfAllowUpdate(post)).thenReturn(true);
        Mockito.when(postService.updatePost(post)).thenReturn(updatedPost);
        Mockito.when(postElasticService.update(post)).thenReturn(postelastic);

        ResponseEntity<Post> response = postUpdateController.update(post);
        Assert.assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    public void updateReturnForbidden() {
        post.setStatus("Đóng");
        Mockito.when(postService.getPostDetails(postId)).thenReturn(java.util.Optional.of(post));
        Mockito.when(postService.checkIfAllowUpdate(post)).thenReturn(false);
        Mockito.when(postService.updatePost(post)).thenReturn(updatedPost);
        Mockito.when(postElasticService.update(post)).thenReturn(postelastic);

        ResponseEntity<Post> response = postUpdateController.update(post);
        Assert.assertEquals(403, response.getStatusCodeValue());
    }
}
