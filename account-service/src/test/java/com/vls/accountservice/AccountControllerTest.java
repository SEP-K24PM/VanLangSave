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

    //save thành công
    @Test
    public void saveUserToDB() {
        UUID id = UUID.randomUUID();
        Account account = new Account("unrealVinh@vanlanguni.vn",id,false);
        Account savedAccount = new Account("unrealVinh@vanlanguni.vn",id,false);
        String email = "unrealVinh@vanlanguni.vn";
        List<Account> accounts = new ArrayList<>();

        Mockito.when(accountRepository.giveAccountInfo(id)).thenReturn(savedAccount);
        Mockito.when(accountRepository.ListAllUser()).thenReturn(accounts);
        Mockito.when(accountRepository.save(account)).thenReturn(savedAccount);
        ResponseEntity<Account> response = accountController.SaveUser(email);
        Assert.assertEquals(201, response.getStatusCodeValue());

    }
    // lấy thông tin thành công
    @Test
    public void  getUserProfile(){
        UUID id = UUID.fromString("14551453-4e68-4e40-9aac-fda12a7b11bc");
        Account finded = new Account("vinh.187pm14034@vanlanguni.vn",id,false);

        Mockito.when(accountRepository.giveAccountInfo(id)).thenReturn(finded);
        ResponseEntity<Account> response = accountController.userProfile(id.toString());
        Assert.assertEquals(200, response.getStatusCodeValue());
        Assert.assertEquals(finded.getEmail(), response.getBody());

    }
    /*
    // lay user profile sai do sai id
    @Test
    public void  FailgetUserProfile(){
        UUID id = UUID.randomUUID();
        UUID id_failed = UUID.randomUUID();
        Account finded = new Account("unrealVinh@vanlanguni.vn",id_failed,false);

        Mockito.when(accountRepository.giveAccountInfo(id)).thenReturn(null);
        ResponseEntity<Account> response = accountController.userProfile(id_failed.toString());
        Assert.assertEquals(404, response.getStatusCodeValue());
    }
     */

}
