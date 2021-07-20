package com.vls.managementservice;

import com.vls.managementservice.controller.AdminController;
import com.vls.managementservice.model.AdminAccount;
import com.vls.managementservice.repository.AdminAccountRepository;
import com.vls.managementservice.repository.PostReportRepository;
import com.vls.managementservice.service.AdminService;
import com.vls.managementservice.service.PostReportService;
import com.vls.managementservice.service.PostService;
import com.vls.managementservice.service.UserAccountService;
import com.vls.managementservice.service.UserHandlingService;

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
import org.springframework.web.client.RestTemplate;

import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
public class AdminControllerTest extends AbstractTest {
    private AdminController adminController;
    private AdminService adminService;
    private ModelMapper modelMapper;
    private PostReportService postReportService;
    private PostService postService;
    private UserAccountService userAccountService;
    private UserHandlingService userHandlingService;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private AdminAccountRepository adminAccountRepository;

    @Mock
    private PostReportRepository postReportRepository;

    @Override
    @Before
    public void setUp() {
        super.setUp();
        adminService = new AdminService(adminAccountRepository);
        modelMapper = new ModelMapper();
        postReportService = new PostReportService(postReportRepository);
        adminController = new AdminController(adminService, modelMapper, postReportService, postService,
            userAccountService, userHandlingService);
    }

    @Test
    public void login() {
        AdminAccount adminAccount = new AdminAccount();
        adminAccount.setEmail("email@email.com");

        Optional<AdminAccount> emptyResult = Optional.empty();
        Mockito.when(adminAccountRepository.findAdmin_AccountByEmailEquals(adminAccount.getEmail())).thenReturn(emptyResult);

        ResponseEntity<AdminAccount> notFoundResponse = adminController.login(adminAccount);
        Assert.assertEquals(404, notFoundResponse.getStatusCodeValue());

        AdminAccount result = new AdminAccount(UUID.randomUUID(), adminAccount.getEmail(), "hashedpassword");
        Mockito.when(adminAccountRepository.findAdmin_AccountByEmailEquals(adminAccount.getEmail())).thenReturn(Optional.of(result));

        ResponseEntity<AdminAccount> response = adminController.login(adminAccount);
        Assert.assertEquals(302,response.getStatusCodeValue());
        Assert.assertEquals(result, response.getBody());
    }

}
