package com.vls.tradeservice;

import com.vls.tradeservice.controller.TradeController;

import com.vls.tradeservice.model.*;
import com.vls.tradeservice.repository.PostRegistrationRepo;
import com.vls.tradeservice.repository.PostRegistrationWithPostRepo;
import com.vls.tradeservice.repository.PostRegistrationWithRelatedEntityRepository;
import com.vls.tradeservice.repository.PostRepository;
import com.vls.tradeservice.service.PostRegistrationService;
import com.vls.tradeservice.service.PostService;
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
public class TradeControllerTest extends AbstractTest {
    private TradeController _tradeController;
    private PostRegistrationService _postRegistrationService;
    private PostService _postService;

    @Mock
    private PostRegistrationRepo _postRegistrationRepo;
    @Mock
    private PostRegistrationWithRelatedEntityRepository _postRegistrationWithRelatedEntityRepository;
    @Mock
    private PostRepository _postRepository;
    @Mock
    private PostRegistrationWithPostRepo postRegistrationWithPostRepo;

    @Override
    @Before
    public void setUp() {
        super.setUp();
        _postRegistrationService = new PostRegistrationService(
                _postRegistrationWithRelatedEntityRepository,
                _postRegistrationRepo,
                postRegistrationWithPostRepo);
        _postService = new PostService(_postRepository);
        _tradeController = new TradeController(_postRegistrationService, _postService);
    }

    @Test
    public void acceptRegisterTest() {
        UUID postRegisterId = UUID.fromString("866d4e59-6974-4ee0-b8b6-347d771f8c76");
        UUID userID = UUID.fromString("14551453-4e68-4e40-9aac-fda12a7b11bc");
        UUID thingID = UUID.fromString("5fad1a4e-e14f-4a7a-a85d-4c1c6547a9c7");
        UUID postID = UUID.fromString("3f552bf8-0bb7-4d5d-b1e2-179844bcd338");
        Thing thing = new Thing();
        Post post = new Post(postID, "description", new Date(), thing,
                "Mở", "Đổi", "fb", UUID.randomUUID(), UUID.randomUUID());
        Post savedPost = new Post(postID, "description", new Date(), thing, "Đóng",
                "Đổi", "fb", UUID.randomUUID(), UUID.randomUUID());

        PostRegistration registration = new PostRegistration(postRegisterId, "description", false,
                thingID, userID, postID);
        PostRegistration saved = new PostRegistration(postRegisterId, "description", true,
                thingID, userID, postID);

        Mockito.when(_postRegistrationRepo.findById(postRegisterId)).thenReturn(java.util.Optional.of(registration));
        Mockito.when(_postRepository.findById(postID)).thenReturn(java.util.Optional.of(post));
        Mockito.when(_postRegistrationRepo.save(registration)).thenReturn(saved);
        Mockito.when(_postRepository.save(post)).thenReturn(savedPost);

        ResponseEntity<Post> response = _tradeController.chooseRegister(postRegisterId);
        Assert.assertEquals(200, response.getStatusCodeValue());

        post.setStatus("Đóng");
        ResponseEntity<Post> forbiddenResponse = _tradeController.chooseRegister(postRegisterId);
        Assert.assertEquals(403, forbiddenResponse.getStatusCodeValue());

        Mockito.when(_postRegistrationRepo.findById(postRegisterId)).thenReturn(Optional.empty());
        ResponseEntity<Post> notFoundResponse = _tradeController.chooseRegister(postRegisterId);
        Assert.assertEquals(404, notFoundResponse.getStatusCodeValue());
    }

    @Test
    public void savePostRegis() {
        UUID postRegisterId = UUID.fromString("866d4e59-6974-4ee0-b8b6-347d771f8c76");
        PostRegistration registration = new PostRegistration( "description", false,
                UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID());
        PostRegistration savedRegistration = new PostRegistration(postRegisterId, "description", false,
                UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID());

        Mockito.when(_postRegistrationRepo.save(registration)).thenReturn(savedRegistration);

        ResponseEntity<PostRegistration> response = _tradeController.SavePostRegistration(registration);
        Assert.assertEquals(201, response.getStatusCodeValue());
        Assert.assertEquals(savedRegistration, response.getBody());
    }

    @Test
    public void getRegisterList() {
        UUID _postID = UUID.randomUUID();
        PostRegistWithEntities postRegist = new PostRegistWithEntities(UUID.randomUUID(),
                "description", false, _postID);
        List<PostRegistWithEntities> list =  new ArrayList<>();
        list.add(postRegist);

        Mockito.when(_postRegistrationWithRelatedEntityRepository.getListRegis(_postID)).thenReturn(list);

        ResponseEntity<List<PostRegistWithEntities>> response = _tradeController.loadListRegister(_postID);
        Assert.assertEquals(200, response.getStatusCodeValue());
        Assert.assertEquals(list, response.getBody());
    }

    @Test
    public void userRegisList() {
        UUID userId = UUID.randomUUID();
        PostRegistrationWithPost postRegistrationWithPost = new PostRegistrationWithPost();
        List<PostRegistrationWithPost> list = new ArrayList<>();
        list.add(postRegistrationWithPost);
        list.add(postRegistrationWithPost);

        Mockito.when(postRegistrationWithPostRepo.getUserRegisList(userId)).thenReturn(list);

        ResponseEntity<List<PostRegistrationWithPost>> response = _tradeController.userRegistList(userId);
        Assert.assertEquals(200, response.getStatusCodeValue());
        Assert.assertEquals(list.size(), response.getBody().size());
    }
}
