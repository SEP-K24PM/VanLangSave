package com.vls.postdeleteservice;

import com.vls.postdeleteservice.controller.PostDeleteController;
import com.vls.postdeleteservice.dto.postelastic;
import com.vls.postdeleteservice.model.Post;
import com.vls.postdeleteservice.model.Thing;
import com.vls.postdeleteservice.service.PostService;
import com.vls.postdeleteservice.service.ThingService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
public class PostDeleteControllerTest extends AbstractTest {
    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @InjectMocks
    private PostDeleteController postDeleteController;

    @Mock
    private PostService postService;

    @Mock
    private ThingService thingService;

    @Test
    public void deletePost() {
        UUID thingId = UUID.randomUUID();

        Post post = new Post("description", new Date(), thingId,
                true, false, "Mở", "Free", "contact");

        Post savedPost = new Post(UUID.randomUUID(),"description", new Date(), UUID.randomUUID(),
                true, false, "Mở", "Free", "contact");

        postelastic postelastic = new postelastic();


    }
}
