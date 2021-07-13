package com.vls.admin_postmanagementservice;


import com.vls.admin_postmanagementservice.controller.HidePostController;
import com.vls.admin_postmanagementservice.model.Post;
import com.vls.admin_postmanagementservice.repository.PostRepository;
import com.vls.admin_postmanagementservice.service.PostService;
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

import java.util.Date;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
public class HidePostControllerTest extends AbstractTest {

    private HidePostController hidePostController;
    private PostService postService;

    @Mock
    private PostRepository postRepository;

    @Override
    @Before
    public void setUp() {
        super.setUp();
        postService = new PostService(postRepository);
        hidePostController = new HidePostController(postService);
    }

    @Test
    public void hidePost() {
        UUID thingId = UUID.randomUUID();

        Post post = new Post(UUID.randomUUID(), "description", new Date(), true, false,
                "contact", thingId, "Free", "Mở");
        Post postupdate = new Post(UUID.randomUUID(), "description", new Date(), false, false,
                "contact", thingId, "Free", "Mở");

        Mockito.when(postRepository.findById(post.getId())).thenReturn(java.util.Optional.of(post));
        Mockito.when(postRepository.save(post)).thenReturn(postupdate);


        ResponseEntity<Boolean> response = hidePostController.hide(post.getId());
        Assert.assertEquals(200, response.getStatusCodeValue());
        Assert.assertEquals(true, response.getBody());


        post.setVisible(false);
        ResponseEntity<Boolean> responseForbidden = hidePostController.hide(post.getId());
        Assert.assertEquals(403, responseForbidden.getStatusCodeValue());

        Mockito.when(postRepository.findById(post.getId())).thenReturn(java.util.Optional.of(new Post()));
        ResponseEntity<Boolean> responseNotFound = hidePostController.hide(UUID.randomUUID());
        Assert.assertEquals(404, responseNotFound.getStatusCodeValue());
    }
}
