package com.vls.tradeservice;

import com.vls.tradeservice.controller.TradeController;

import com.vls.tradeservice.model.Post;
import com.vls.tradeservice.model.PostRegistWithEntities;
import com.vls.tradeservice.model.PostRegistration;
import com.vls.tradeservice.repository.PostRegistrationRepo;
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


    @Override
    @Before
    public void setUp() {
        super.setUp();
        _postRegistrationService = new PostRegistrationService(_postRegistrationWithRelatedEntityRepository, _postRegistrationRepo);
        _postService = new PostService(_postRepository);
        _tradeController = new TradeController(_postRegistrationService, _postService);
    }

    @Test
    public void acceptRegisterTest() {
        UUID postRegisterId = UUID.fromString("866d4e59-6974-4ee0-b8b6-347d771f8c76");
        UUID userID = UUID.fromString("14551453-4e68-4e40-9aac-fda12a7b11bc");
        UUID thingID = UUID.fromString("5fad1a4e-e14f-4a7a-a85d-4c1c6547a9c7");
        UUID postID = UUID.fromString("3f552bf8-0bb7-4d5d-b1e2-179844bcd338");
        Post post = new Post(postID, "description", new Date(), thingID, "Mở", "Đổi", "fb");
        Post savedPost = new Post(postID, "description", new Date(), thingID, "Đóng", "Đổi", "fb");

        PostRegistration registration = new PostRegistration(postRegisterId, "description", false, thingID, userID, postID);
        PostRegistration saved = new PostRegistration(postRegisterId, "description", true, thingID, userID, postID);

        Mockito.when(_postRegistrationRepo.findById(postRegisterId)).thenReturn(java.util.Optional.of(registration));
        Mockito.when(_postRepository.findById(postID)).thenReturn(java.util.Optional.of(post));
        Mockito.when(_postRegistrationRepo.save(registration)).thenReturn(saved);
        Mockito.when(_postRepository.save(post)).thenReturn(savedPost);

        ResponseEntity<PostRegistration> response = _tradeController.chooseRegister(postRegisterId);
        Assert.assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    public void acceptRegisterForbiddenTest() {
        UUID postRegisterId = UUID.fromString("866d4e59-6974-4ee0-b8b6-347d771f8c76");
        UUID userID = UUID.fromString("14551453-4e68-4e40-9aac-fda12a7b11bc");
        UUID thingID = UUID.fromString("5fad1a4e-e14f-4a7a-a85d-4c1c6547a9c7");
        UUID postID = UUID.fromString("3f552bf8-0bb7-4d5d-b1e2-179844bcd338");
        Post post = new Post(postID, "description", new Date(), thingID, "Đóng", "Đổi", "fb");

        PostRegistration registration = new PostRegistration(postRegisterId, "description", false, thingID, userID, postID);

        Mockito.when(_postRegistrationRepo.findById(postRegisterId)).thenReturn(java.util.Optional.of(registration));
        Mockito.when(_postRepository.findById(postID)).thenReturn(java.util.Optional.of(post));

        ResponseEntity<PostRegistration> response = _tradeController.chooseRegister(postRegisterId);
        Assert.assertEquals(403, response.getStatusCodeValue());
    }

    @Test
    public void acceptRegisterNotFoundTest() {
        UUID postRegisterId = UUID.fromString("866d4e59-6974-4ee0-b8b6-347d771f8c76");

        Mockito.when(_postRegistrationRepo.findById(postRegisterId)).thenReturn(Optional.empty());

        ResponseEntity<PostRegistration> response = _tradeController.chooseRegister(postRegisterId);
        Assert.assertEquals(404, response.getStatusCodeValue());
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

        ResponseEntity response = _tradeController.loadListRegister(_postID);
        Assert.assertEquals(200, response.getStatusCodeValue());
        Assert.assertEquals(list, response.getBody());
    }
}
