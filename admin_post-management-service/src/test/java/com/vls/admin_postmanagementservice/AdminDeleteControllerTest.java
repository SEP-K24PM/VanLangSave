package com.vls.admin_postmanagementservice;

import com.vls.admin_postmanagementservice.controller.AdminDeleteController;
import com.vls.admin_postmanagementservice.model.Post;
import com.vls.admin_postmanagementservice.repository.PostRepository;
import com.vls.admin_postmanagementservice.service.PostService;
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
public class AdminDeleteControllerTest extends AbstractTest {

    private AdminDeleteController adminDeleteController;
    private PostService postService;

    @Mock
    private PostRepository postRepository;

    @Override
    @Before
    public void setUp() {
        super.setUp();
        postService = new PostService(postRepository);
        adminDeleteController = new AdminDeleteController(postService);
    }


    @Test
    public void deletePost() {
        UUID thingId = UUID.randomUUID();

        Post post = new Post(UUID.randomUUID(), "description", new Date(), thingId,
                true, false, "Mở", "Free", "contact");

        Mockito.when(postRepository.findById(post.getId())).thenReturn(java.util.Optional.of(post));
        Mockito.doNothing().when(postRepository).delete(post);

        ResponseEntity<Boolean> response = adminDeleteController.delete(post.getId());
        Assert.assertEquals(200, response.getStatusCodeValue());
        Assert.assertEquals(true, response.getBody());

        post.setDeletion(true);
        ResponseEntity<Boolean> responseForbidden = adminDeleteController.delete(post.getId());
        Assert.assertEquals(403, responseForbidden.getStatusCodeValue());

        Mockito.when(postRepository.findById(post.getId())).thenReturn(java.util.Optional.of(new Post()));
        ResponseEntity<Boolean> responseNotFound = adminDeleteController.delete(UUID.randomUUID());
        Assert.assertEquals(404, responseNotFound.getStatusCodeValue());
    }

}
