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

        Mockito.when(accountRepository.giveAccountInfo(email)).thenReturn(account);
        Mockito.when(accountRepository.ListAllUser()).thenReturn(accounts);
        Mockito.when(accountRepository.save(account)).thenReturn(savedAccount);
        ResponseEntity<Account> response = accountController.SaveUser(email);
        Assert.assertEquals(201, response.getStatusCodeValue());
        //Assert.assertEquals(savedAccount, response.getBody());

    }
    @Test
    public void  getUserProfile(){
        //UUID id = (UUID) "14551453-4e68-4e40-9aac-fda12a7b11bc";
        //Account account = new Account("unrealVinh@vanlanguni.vn",id,false);
        Account finded = new Account("vinh.187pm14034@vanlanguni.vn");
        String email = "vinh.187pm14034@vanlanguni.vn";
        //List<Account> accounts = new ArrayList<>();

        Mockito.when(accountRepository.giveAccountInfo(email)).thenReturn(finded);
        //Mockito.when(accountRepository.ListAllUser()).thenReturn(accounts);
        ResponseEntity<Account> response = accountController.userProfile(email);
        Assert.assertEquals(200, response.getStatusCodeValue());
        //Assert.assertEquals(finded.getEmail(), response.getBody().getEmail());
    }
    @Test
    public void  FailgetUserProfile(){
        //UUID id = (UUID) "14551453-4e68-4e40-9aac-fda12a7b11bc";
        //Account account = new Account("unrealVinh@vanlanguni.vn",id,false);
        Account finded = new Account("vinh.187pm14034@vanlanguni.vn");
        String email = "vinh123.187pm14034@vanlanguni.vn";
        //List<Account> accounts = new ArrayList<>();

        Mockito.when(accountRepository.giveAccountInfo(email)).thenReturn(finded);
        //Mockito.when(accountRepository.ListAllUser()).thenReturn(accounts);
        ResponseEntity<Account> response = accountController.userProfile(email);
        Assert.assertEquals(404, response.getStatusCodeValue());
        //Assert.assertEquals(finded.getEmail(), response.getBody().getEmail());
    }
}
