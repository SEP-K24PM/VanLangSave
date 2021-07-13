package com.vls.accountservice;

import com.vls.accountservice.controller.AccountController;
import com.vls.accountservice.model.Account;
import com.vls.accountservice.repository.AccountRepository;
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
public class AccountControllerTest extends AbstractTest {
    private AccountController accountController;

    @Mock
    private AccountRepository accountRepository;

    @Override
    @Before
    public void setUp() {
        super.setUp();
//        accountController = new AccountController(accountRepository, modelMapper);
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

}
