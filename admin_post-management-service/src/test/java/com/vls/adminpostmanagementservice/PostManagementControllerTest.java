package com.vls.adminpostmanagementservice;

import DTO.PostDTO;
import com.vls.adminpostmanagementservice.controller.PostManagementController;
import com.vls.adminpostmanagementservice.model.Post;
import com.vls.adminpostmanagementservice.repository.PostRepository;
import com.vls.adminpostmanagementservice.service.PostService;
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

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
public class PostManagementControllerTest extends AbstractTest {
    private PostManagementController postManagementController;
    private ModelMapper modelMapper;
    private PostService postService;

    @Mock
    private PostRepository postRepository;

    @Override
    @Before
    public void setUp() {
        super.setUp();
        postService = new PostService(postRepository);
        modelMapper = new ModelMapper();
        postManagementController = new PostManagementController(postService, modelMapper);
    }

    @Test
    public void hidePost() {
        Post post = new Post(UUID.randomUUID(), "description", new Date(), UUID.randomUUID(),
                true, false, "Mở", "Free", "contact");
        Post postupdate = new Post(post.getId(), "description", new Date(), post.getThing_id(), false,
                true, "Mở", "Free", "contact");
        PostDTO postDTO = new PostDTO(postupdate.getId(), postupdate.getDescription(), postupdate.getCreated_time(),
                postupdate.isVisible(), postupdate.isDeletion(), postupdate.getContact(), postupdate.getExchange_method(),
                postupdate.getStatus(), postupdate.getThing_id());
        Mockito.when(postRepository.findById(post.getId())).thenReturn(java.util.Optional.of(post));
        Mockito.when(postRepository.save(post)).thenReturn(postupdate);

        ResponseEntity<PostDTO> response = postManagementController.hide(post.getId());
        Assert.assertEquals(200, response.getStatusCodeValue());
        PostDTO actual = response.getBody();
        Assert.assertEquals(postDTO.getId(), actual.getId());
        Assert.assertEquals(postDTO.getDescription(), actual.getDescription());
        Assert.assertEquals(postDTO.getCreated_time(), actual.getCreated_time());
        Assert.assertEquals(postDTO.getStatus(), actual.getStatus());
        Assert.assertEquals(postDTO.getContact(), actual.getContact());
        Assert.assertEquals(postDTO.getExchange_method(), actual.getExchange_method());
        Assert.assertEquals(postDTO.isDeletion(), actual.isDeletion());
        Assert.assertEquals(postDTO.isVisible(), actual.isVisible());

        Mockito.when(postRepository.findById(post.getId())).thenReturn(Optional.empty());
        ResponseEntity<PostDTO> responseNotFound = postManagementController.hide(UUID.randomUUID());
        Assert.assertEquals(404, responseNotFound.getStatusCodeValue());
    }

    @Test
    public void deletePost() {
        UUID thingId = UUID.randomUUID();

        Post post = new Post(UUID.randomUUID(), "description", new Date(), thingId,
                true, false, "Mở", "Free", "contact");
        Post softDeletePost = new Post(UUID.randomUUID(), "description", new Date(), thingId,
                true, true, "Mở", "Free", "contact");
        PostDTO postDTO = new PostDTO(softDeletePost.getId(), softDeletePost.getDescription(), softDeletePost.getCreated_time(),
                softDeletePost.isVisible(), softDeletePost.isDeletion(), softDeletePost.getContact(), softDeletePost.getExchange_method(),
                softDeletePost.getStatus(), softDeletePost.getThing_id());

        Mockito.when(postRepository.findById(post.getId())).thenReturn(java.util.Optional.of(post));
        Mockito.when(postRepository.save(post)).thenReturn(softDeletePost);

        ResponseEntity<PostDTO> response = postManagementController.delete(post.getId());
        Assert.assertEquals(200, response.getStatusCodeValue());

        Mockito.when(postRepository.findById(post.getId())).thenReturn(Optional.empty());
        ResponseEntity<PostDTO> responseNotFound = postManagementController.delete(UUID.randomUUID());
        Assert.assertEquals(404, responseNotFound.getStatusCodeValue());
    }
}
