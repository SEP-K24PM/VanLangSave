package com.vls.accountservice;

import com.vls.accountservice.controller.AccountController;
import com.vls.accountservice.model.Account;
import com.vls.accountservice.repository.AccountRepository;
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
public class AccountControllerTest extends AbstractTest {
    private AccountController accountController;

    @Mock
    private AccountRepository accountRepository;

    @Override
    @Before
    public void setUp() {
        super.setUp();
        accountController = new AccountController(accountRepository);
    }

//    @InjectMocks
//    private AccountController accountController;

    @Test
    public void saveUserToDB() {
        UUID id = UUID.randomUUID();
        Account account = new Account("unrealVinh@vanlanguni.vn");
        Account savedAccount = new Account("unrealVinh@vanlanguni.vn");
        String email = "unrealVinh@vanlanguni.vn";
        List<Account> accounts = new ArrayList<>();

//        Mockito.when(accountController.checked(email)).thenReturn(true);
        Mockito.when(accountRepository.giveAccountInfo(email)).thenReturn(account);
        Mockito.when(accountRepository.ListAllUser()).thenReturn(accounts);
        Mockito.when(accountRepository.save(account)).thenReturn(savedAccount);
        ResponseEntity<Account> response = accountController.SaveUser(email);
        Assert.assertEquals(201, response.getStatusCodeValue());
        Assert.assertEquals(savedAccount, response.getBody());
        /*
        Post post = new Post("description", new Date(), thingId,
                true, false, "Mở", "Free", "contact");

        Post savedPost = new Post(UUID.randomUUID(),"description", new Date(), UUID.randomUUID(),
                true, false, "Mở", "Free", "contact");

        postelastic postelastic = new postelastic();

        Mockito.when(postService.checkThingIsAvailable(thingId)).thenReturn(true);
        Mockito.when(postService.createPost(post)).thenReturn(savedPost);
        Mockito.when(rabbitMQSender.convertToPostElastic(savedPost)).thenReturn(postelastic);
        Mockito.when(thingService.updateThingWithNewPost(savedPost.getThing_id(), savedPost.getId())).thenReturn(null);
        Thing thing = new Thing("name", "origin", 1000, 1, "used time", "image", UUID.randomUUID(),
                UUID.randomUUID(), null);

        ResponseEntity<Post> response = postSaveController.createPost(post);
        Assert.assertEquals(201, response.getStatusCodeValue());
        Assert.assertEquals(savedPost, response.getBody());

        Mockito.when(postService.checkThingIsAvailable(thingId)).thenReturn(false);
        ResponseEntity<Post> responseForbidden = postSaveController.createPost(post);
        Assert.assertEquals(403, responseForbidden.getStatusCodeValue());

         */
    }
}
