package com.vls.admin_usermanagementservice;

import com.vls.admin_usermanagementservice.controller.UserManagementController;
import com.vls.admin_usermanagementservice.model.UserAccount;
import com.vls.admin_usermanagementservice.repository.UserAccountRepository;
import com.vls.admin_usermanagementservice.service.UserAccountService;
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

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
public class UserManagementControllerTest extends AbstractTest{
    private UserManagementController userManagementController;
    private UserAccountService userAccountService;

    @Mock
    private UserAccountRepository userAccountRepository;

    @Override
    @Before
    public void setUp() {
        super.setUp();
        userAccountService = new UserAccountService(userAccountRepository);
        userManagementController = new UserManagementController(userAccountService);
    }

    @Test
    public void listTest() {
        UserAccount userAccount = new UserAccount(UUID.randomUUID(), "email@gmial.com", false);
        List<UserAccount> list = new ArrayList<>();
        list.add(userAccount);
        list.add(userAccount);

        Mockito.when(userAccountRepository.findAll()).thenReturn(list);

        ResponseEntity<List<UserAccount>> response = userManagementController.listUserAccount();
        Assert.assertEquals(200, response.getStatusCodeValue());
        Assert.assertEquals(list, response.getBody());
    }

    @Test
    public void blockUserTest() {
        UserAccount userAccount = new UserAccount(UUID.randomUUID(), "email@gmial.com", false);
        UserAccount savedUserAccount = new UserAccount(UUID.randomUUID(), "email@gmial.com", true);
        Mockito.when(userAccountRepository.save(userAccount)).thenReturn(savedUserAccount);

        ResponseEntity<UserAccount> response = userManagementController.blockUser(userAccount);
        Assert.assertEquals(200, response.getStatusCodeValue());
        Assert.assertEquals(savedUserAccount, response.getBody());
    }
}
