package com.vls.accountservice;

import com.vls.accountservice.controller.UserAccountController;
import com.vls.accountservice.model.User_Account;
import com.vls.accountservice.repository.UserAccountRepository;
import com.vls.accountservice.service.UserAccountService;
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

import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
public class UserAccountControllerTest extends AbstractTest {
    private UserAccountController userAccountController;
    private UserAccountService userAccountService;

    @Mock
    private UserAccountRepository userAccountRepository;

    @Override
    @Before
    public void setUp() {
        super.setUp();
        userAccountService = new UserAccountService(userAccountRepository);
        userAccountController = new UserAccountController(userAccountRepository, userAccountService);
    }

    @Test
    public void login() {
        User_Account userAccount = new User_Account();
        userAccount.setEmail("email@vanlanguni.vn");
        User_Account foundUser = new User_Account(UUID.randomUUID(), userAccount.getEmail(), false);

        Mockito.when(userAccountRepository.findAccountByEmailEquals(userAccount.getEmail())).thenReturn(Optional.of(foundUser));
        ResponseEntity<User_Account> foundResponse = userAccountController.login(userAccount);
        Assert.assertEquals(302,foundResponse.getStatusCodeValue());
        Assert.assertEquals(foundUser, foundResponse.getBody());


        Optional<User_Account> emptyResult = Optional.empty();
        User_Account savedUser = new User_Account(UUID.randomUUID(), userAccount.getEmail(), false);
        Mockito.when(userAccountRepository.findAccountByEmailEquals(userAccount.getEmail())).thenReturn(emptyResult);
        Mockito.when(userAccountRepository.save(userAccount)).thenReturn(savedUser);

        ResponseEntity<User_Account> response = userAccountController.login(userAccount);
        Assert.assertEquals(201, response.getStatusCodeValue());
    }

}
