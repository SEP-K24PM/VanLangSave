package com.vls.tradeservice;

import com.vls.tradeservice.controller.tradeController;
import com.vls.tradeservice.model.PostRegistration;

import com.vls.tradeservice.repository.PostRegistrationRepo;
import com.vls.tradeservice.repository.PostRepo;
import com.vls.tradeservice.repository.ThingRepo;
import com.vls.tradeservice.repository.UserRepo;
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

import java.util.UUID;

@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
public class tradeControllerTest extends AbstractTest {

    private tradeController _tradeController;

    @Mock
    private PostRegistrationRepo _postRegistrationRepo;
    @Mock
    private PostRepo _postRepo;
    @Mock
    private ThingRepo _thingrepo;
    @Mock
    private UserRepo _userrepo;


    @Override
    @Before
    public void setUp() {
        super.setUp();
        _tradeController = new tradeController(_postRegistrationRepo, _thingrepo,_postRepo,_userrepo);
    }

    @Test
    public void saveToDB() {
        UUID _userID = UUID.fromString("14551453-4e68-4e40-9aac-fda12a7b11bc");
        UUID _thingID = UUID.fromString("5fad1a4e-e14f-4a7a-a85d-4c1c6547a9c7");
        UUID _postID = UUID.fromString("3f552bf8-0bb7-4d5d-b1e2-179844bcd338");

        PostRegistration registration = new PostRegistration(_thingID,_userID,_postID);
        PostRegistration saved = new PostRegistration(_thingID,_userID,_postID);

        Mockito.when(_postRegistrationRepo.save(registration)).thenReturn(saved);
        ResponseEntity response = _tradeController.SavePostRegistration();
        Assert.assertEquals(201, response.getStatusCodeValue());
        //Assert.assertEquals(saved, response.getBody());
    }
}
