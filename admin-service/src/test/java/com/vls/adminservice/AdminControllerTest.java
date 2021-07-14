package com.vls.adminservice;

import com.vls.adminservice.controller.AdminController;
import com.vls.adminservice.model.Admin_Account;
import com.vls.adminservice.repository.AdminAccountRepository;
import com.vls.adminservice.service.AdminService;
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
import org.springframework.web.client.RestTemplate;

import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
public class AdminControllerTest extends AbstractTest {
    private AdminController adminController;
    private AdminService adminService;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private AdminAccountRepository adminAccountRepository;

    @Override
    @Before
    public void setUp() {
        super.setUp();
        adminService = new AdminService(adminAccountRepository);
        adminController = new AdminController(adminService, restTemplate);
    }

    @Test
    public void login() {
        Admin_Account adminAccount = new Admin_Account();
        adminAccount.setEmail("email@email.com");

        Optional<Admin_Account> emptyResult = Optional.empty();
        Mockito.when(adminAccountRepository.findAdmin_AccountByEmailEquals(adminAccount.getEmail())).thenReturn(emptyResult);

        ResponseEntity<Admin_Account> notFoundResponse = adminController.login(adminAccount);
        Assert.assertEquals(404, notFoundResponse.getStatusCodeValue());

        Admin_Account result = new Admin_Account(UUID.randomUUID(), adminAccount.getEmail(), "hashedpassword");
        Mockito.when(adminAccountRepository.findAdmin_AccountByEmailEquals(adminAccount.getEmail())).thenReturn(Optional.of(result));

        ResponseEntity<Admin_Account> response = adminController.login(adminAccount);
        Assert.assertEquals(302,response.getStatusCodeValue());
        Assert.assertEquals(result, response.getBody());
    }

}
