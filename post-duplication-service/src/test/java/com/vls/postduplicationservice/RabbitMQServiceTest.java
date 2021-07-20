package com.vls.postduplicationservice;

import com.vls.postduplicationservice.Model.postelastic;
import com.vls.postduplicationservice.repository.PostRepository;
import com.vls.postduplicationservice.service.RabbitMQService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
public class RabbitMQServiceTest extends AbstractTest {
    RabbitMQService rabbitMQService;

    @Mock
    private PostRepository postRepository;

    @Override
    @Before
    public void setUp() {
        super.setUp();
        rabbitMQService = new RabbitMQService(postRepository);
    }

    @Test
    public void RabbitMQTest() {
        postelastic postelastic = new postelastic(UUID.randomUUID().toString(), "description",
                "exchange method", new Date(), true, "thing name", "origin", "category name", "image");

        Mockito.when(postRepository.save(postelastic)).thenReturn(postelastic);
        rabbitMQService.receivedMessage(postelastic);
    }
}
