package com.vls.managementservice;


import DTO.UserAccountDTO;
import com.vls.managementservice.controller.UserController;
import com.vls.managementservice.model.*;
import com.vls.managementservice.repository.AccountRepository;
import com.vls.managementservice.repository.PostRepository;
import com.vls.managementservice.repository.User_AccountRepository;
import com.vls.managementservice.service.UserService;
import com.vls.managementservice.service.User_AccountService;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
public class UserControllerTest extends AbstractTest {
    private UserController userController;
    private UserService userService;
    private User_AccountService userAccountService;
    private ModelMapper modelMapper;

    @Mock
    private AccountRepository accountRepository;
    @Mock
    private User_AccountRepository userAccountRepository;
    @Mock
    private PostRepository postRepository;

    @Override
    @Before
    public void setUp() {
        super.setUp();
        userAccountService = new User_AccountService(userAccountRepository);
        modelMapper = new ModelMapper();
        userService = new UserService(postRepository, modelMapper);
        userController = new UserController(userAccountService, accountRepository, modelMapper, userService);
    }

    private User_Account getUserAccount() {
        return new User_Account(UUID.randomUUID(), "email@vanlanguni.vn", false);
    }

    @Test
    public void loginTest() {
        User_Account user_account = getUserAccount();
        User_Account savedUser = getUserAccount();

        Mockito.when(userAccountRepository.findAccountByEmailEquals(user_account.getEmail())).thenReturn(java.util.Optional.of(user_account));

        ResponseEntity<User_Account> response = userController.login(user_account);
        Assert.assertEquals(302, response.getStatusCodeValue());
        Assert.assertEquals(user_account, response.getBody());

        Mockito.when(userAccountRepository.findAccountByEmailEquals(user_account.getEmail())).thenReturn(Optional.empty());
        Mockito.when(userAccountRepository.save(user_account)).thenReturn(savedUser);
        ResponseEntity<User_Account> savedResponse = userController.login(user_account);
        Assert.assertEquals(201, savedResponse.getStatusCodeValue());

        user_account.setEmail("email@nomail.com");
        ResponseEntity<User_Account> forbiddenResponse = userController.login(user_account);
        Assert.assertEquals(403, forbiddenResponse.getStatusCodeValue());
    }

    @Test
    public void profileTest() {
        Account info = new Account(UUID.randomUUID(), "email@vanlanguni.vn", false);

        List<UserRating> userRatingList = new ArrayList<>();
        UserRating userRating = new UserRating(UUID.randomUUID(), "description", 5);
        userRatingList.add(userRating);
        userRatingList.add(userRating);

        Thing thing = new Thing();
        thing.setId(UUID.randomUUID());
        List<Thing> thingList = new ArrayList<>();
        thingList.add(thing);
        thingList.add(thing);

        Post post = new Post();
        post.setThing_id(thing.getId());

        info.setThings(thingList);
        info.setRatings(userRatingList);

        Mockito.when(accountRepository.giveAccountInfo(info.getId())).thenReturn(info);
        Mockito.when(postRepository.findByThingIdEquals(thing.getId())).thenReturn(post);

        ResponseEntity<UserAccountDTO> response = userController.userProfile(info.getId().toString());
        Assert.assertEquals(200, response.getStatusCodeValue());

        info.setEmail(null);
        ResponseEntity<UserAccountDTO> notFoundResponse = userController.userProfile(info.getId().toString());
        Assert.assertEquals(404, notFoundResponse.getStatusCodeValue());
    }
}
