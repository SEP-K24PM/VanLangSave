package com.vls.adminpostmanagementservice;


import DTO.PostDTO;
import com.vls.adminpostmanagementservice.controller.HidePostController;
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
public class HidePostControllerTest extends AbstractTest {

    private HidePostController hidePostController;
    private PostService postService;
    private ModelMapper modelMapper;

    @Mock
    private PostRepository postRepository;

    @Override
    @Before
    public void setUp() {
        super.setUp();
        postService = new PostService(postRepository);
        modelMapper = new ModelMapper();
        hidePostController = new HidePostController(postService, modelMapper);
    }

    @Test
    public void hidePost() {
        Post post = new Post(UUID.randomUUID(), "description", new Date(), true, false,
                "contact", UUID.randomUUID(), "Free", "Mở");
        Post postupdate = new Post(post.getId(), "description", new Date(), false, false,
                "contact", post.getThing_id(), "Free", "Mở");
        PostDTO postDTO = new PostDTO(postupdate.getId(), postupdate.getDescription(), postupdate.getCreated_time(),
                postupdate.getVisible(), postupdate.getDeletion(), postupdate.getContact(), postupdate.getExchange_method(),
                postupdate.getStatus());
        Mockito.when(postRepository.findById(post.getId())).thenReturn(java.util.Optional.of(post));
        Mockito.when(postRepository.save(post)).thenReturn(postupdate);

        ResponseEntity<PostDTO> response = hidePostController.hide(post.getId().toString());
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
        ResponseEntity<PostDTO> responseNotFound = hidePostController.hide(UUID.randomUUID().toString());
        Assert.assertEquals(404, responseNotFound.getStatusCodeValue());
    }
}
