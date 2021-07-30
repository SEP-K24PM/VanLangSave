package com.vls.managementservice;

import Constants.ActionConstants;
import DTO.PostDTO;
import DTO.PostReportDTO;
import DTO.UserAccountDTO;
import DTO.UserHandlingDTO;
import com.vls.managementservice.controller.AdminController;
import com.vls.managementservice.model.*;
import com.vls.managementservice.repository.*;
import com.vls.managementservice.service.AdminService;
import com.vls.managementservice.service.PostReportService;
import com.vls.managementservice.service.PostService;
import com.vls.managementservice.service.UserAccountService;
import com.vls.managementservice.service.UserHandlingService;

import org.apache.catalina.User;
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

import java.util.*;

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
    private AdminAccountRepository adminAccountRepository;
    @Mock
    private PostReportRepository postReportRepository;
    @Mock
    private PostRepository postRepository;
    @Mock
    private PostWTRepository postWTRepository;
    @Mock
    private UserAccountRepository userAccountRepository;
    @Mock
    private UserHandlingRepository userHandlingRepository;

    @Override
    @Before
    public void setUp() {
        super.setUp();
        adminService = new AdminService(adminAccountRepository);
        modelMapper = new ModelMapper();
        postReportService = new PostReportService(postReportRepository);
        postService = new PostService(postRepository, postWTRepository);
        userAccountService = new UserAccountService(userAccountRepository);
        userHandlingService = new UserHandlingService(userHandlingRepository);
        adminController = new AdminController(adminService, modelMapper, postReportService, postService,
            userAccountService, userHandlingService);
    }

    private Post getPost() {
        return new Post(UUID.randomUUID(),
            "description", new Date(), UUID.randomUUID(), true, false,
                "Mở","Đổi","contact", UUID.randomUUID(), UUID.randomUUID());
    }

    private PostDTO getPostDTO() {
        return new PostDTO(UUID.randomUUID(), "description", new Date(), true, false, "contact",
                "Đổi", "Mở", UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID());
    }

    private PostReport getPostReport() {
        return new PostReport(UUID.randomUUID(), "description","handling", "reason");
    }

    private PostReportDTO getPostReportDTO() {
        return new PostReportDTO(UUID.randomUUID(), "description", "handling", "reason",
                UUID.randomUUID(),
                UUID.randomUUID(),
                UUID.randomUUID());
    }

    private UserAccount getUserAccount() {
        return new UserAccount(UUID.randomUUID(), "email@nomail.com", false);
    }

    private UserHandling getUserHandling() {
        return new UserHandling(UUID.randomUUID(), "reason", UUID.randomUUID(), UUID.randomUUID(), new Date());
    }

    @Test
    public void loginTest() {
        AdminAccount adminAccount = new AdminAccount();
        adminAccount.setEmail("email@email.com");
        adminAccount.setPwd("hashedPassword");

        Optional<AdminAccount> emptyResult = Optional.empty();
        Mockito.when(adminAccountRepository.findAdmin_AccountByEmailEquals(adminAccount.getEmail())).thenReturn(emptyResult);

        ResponseEntity<AdminAccount> notFoundResponse = adminController.login(adminAccount);
        Assert.assertEquals(404, notFoundResponse.getStatusCodeValue());

        AdminAccount result = new AdminAccount(UUID.randomUUID(), adminAccount.getEmail(), "hashedPassword");
        Mockito.when(adminAccountRepository.findAdmin_AccountByEmailEquals(adminAccount.getEmail())).thenReturn(Optional.of(result));

        ResponseEntity<AdminAccount> response = adminController.login(adminAccount);
        Assert.assertEquals(202, response.getStatusCodeValue());
        Assert.assertEquals(result, response.getBody());

        adminAccount.setPwd("wrongpassword");
        ResponseEntity<AdminAccount> forbiddenResponse = adminController.login(adminAccount);
        Assert.assertEquals(403, forbiddenResponse.getStatusCodeValue());
        Assert.assertEquals(result, response.getBody());
    }

    @Test
    public void listReportTest() {
        PostReport postReport = getPostReport();
        List<PostReport> list = new ArrayList<>();
        list.add(postReport);
        list.add(postReport);

        Mockito.when(postReportRepository.findAll()).thenReturn(list);

        ResponseEntity<List<PostReportDTO>> response = adminController.listReport();
        Assert.assertEquals(200, response.getStatusCodeValue());
        Assert.assertEquals(list.size(), response.getBody().size());
    }

    @Test
    public void detailsReportTest() {
        PostReport postReport = getPostReport();

        Mockito.when(postReportRepository.findById(postReport.getId())).thenReturn(Optional.of(postReport));

        ResponseEntity<PostReportDTO> response = adminController.details(postReport.getId().toString());
        Assert.assertEquals(200, response.getStatusCodeValue());
        PostReportDTO result = response.getBody();
        Assert.assertEquals(postReport.getId(), result.getId());
        Assert.assertEquals(postReport.getDescription(), result.getDescription());
        Assert.assertEquals(postReport.getHandling(), result.getHandling());
        Assert.assertEquals(postReport.getReason_by_admin(), result.getReason_by_admin());

        Mockito.when(postReportRepository.findById(postReport.getId())).thenReturn(Optional.empty());
        ResponseEntity<PostReportDTO> notFoundResponse = adminController.details(postReport.getId().toString());
        Assert.assertEquals(404, notFoundResponse.getStatusCodeValue());
    }
    @Test
    public void handlePostReportTest() {
        PostDTO postDTO = getPostDTO();
        PostReportDTO postReportDTO = getPostReportDTO();
        postReportDTO.setHandling(ActionConstants.PostHandling.DELETE_POST);
        postReportDTO.setPost(postDTO);

        Post post = getPost();
        post.setId(postReportDTO.getPost_id());

        Mockito.when(postRepository.findById(postReportDTO.getPost().getId())).thenReturn(Optional.of(post));
        Mockito.when(postRepository.save(post)).thenReturn(post);

        ResponseEntity<PostReportDTO> deletedResponse = adminController.hidePost(postReportDTO);
        Assert.assertEquals(200, deletedResponse.getStatusCodeValue());

        postReportDTO.setHandling(ActionConstants.PostHandling.HIDE_POST);
        ResponseEntity<PostReportDTO> hiddenResponse = adminController.hidePost(postReportDTO);
        Assert.assertEquals(200, hiddenResponse.getStatusCodeValue());
    }

    @Test
    public void listUsersTest() {
        UserAccount userAccount = getUserAccount();
        List<UserAccount> list = new ArrayList<>();
        list.add(userAccount);
        list.add(userAccount);

        Mockito.when(userAccountRepository.findAll()).thenReturn(list);

        ResponseEntity<List<UserAccountDTO>> response = adminController.listUser();
        Assert.assertEquals(200, response.getStatusCodeValue());
        Assert.assertEquals(list.size(), response.getBody().size());
    }

    @Test
    public void getUserHandlingTest() {
        String userId = UUID.randomUUID().toString();

        UserHandling userHandling = getUserHandling();
        List<UserHandling> userHandlingList = new ArrayList<>();
        userHandlingList.add(userHandling);
        userHandlingList.add(userHandling);
        userHandlingList.add(userHandling);
        UserAccount userAccount = getUserAccount();

        UserHandlingDTO userHandlingDTO = new UserHandlingDTO(userHandling.getId(),
                userHandling.getReason(), userHandling.getUser_id(), userHandling.getAdmin_id(), userHandling.getTime());
        List<UserHandlingDTO> list = new ArrayList<>();
        list.add(userHandlingDTO);
        list.add(userHandlingDTO);
        list.add(userHandlingDTO);

        UserAccountDTO userAccountDTO = new UserAccountDTO(userAccount.getId(), userAccount.getEmail(), userAccount.isBlock());
        userAccountDTO.setUserHandlingList(list);

        Mockito.when(userAccountRepository.findById(UUID.fromString(userId))).thenReturn(Optional.of(userAccount));
        Mockito.when(userHandlingRepository.findHandlingByUserId(UUID.fromString(userId))).thenReturn(userHandlingList);

        ResponseEntity<UserAccountDTO> respsone = adminController.getUserHandling(userId);
        Assert.assertEquals(200, respsone.getStatusCodeValue());
        UserAccountDTO result = respsone.getBody();
        Assert.assertEquals(userAccountDTO.getEmail(), result.getEmail());
        Assert.assertEquals(userAccountDTO.getId(), result.getId());
        Assert.assertEquals(userAccountDTO.isBlock(), result.isBlock());
        Assert.assertEquals(userAccountDTO.getUserHandlingList().size(), result.getUserHandlingList().size());


        Mockito.when(userAccountRepository.findById(UUID.fromString(userId))).thenReturn(Optional.empty());
        ResponseEntity<UserAccountDTO> notFoundResponse = adminController.getUserHandling(userId);
        Assert.assertEquals(404, notFoundResponse.getStatusCodeValue());
    }

    @Test
    public void blockUserTest() {
        String userId = UUID.randomUUID().toString();
        UserAccount userAccount = getUserAccount();

        UserHandling userHandling = getUserHandling();
        userHandling.setUser_id(UUID.fromString(userId));
        UserHandlingDTO userHandlingDTO = new UserHandlingDTO(userHandling.getId(),
                userHandling.getReason(), UUID.fromString(userId), userHandling.getAdmin_id(), userHandling.getTime());

        Mockito.when(userAccountRepository.findById(UUID.fromString(userId))).thenReturn(Optional.of(userAccount));
        Mockito.when(userAccountRepository.save(userAccount)).thenReturn(userAccount);
        Mockito.when(userHandlingRepository.save(userHandling)).thenReturn(userHandling);

        ResponseEntity<UserHandlingDTO> response = adminController.blockUser(userHandling);
        Assert.assertEquals(200, response.getStatusCodeValue());
        UserHandlingDTO result = response.getBody();
        Assert.assertEquals(userHandlingDTO.getId(), result.getId());
        Assert.assertEquals(userHandlingDTO.getReason(), result.getReason());
    }
}
