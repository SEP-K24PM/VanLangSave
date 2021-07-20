package com.vls.managementservice.controller;

import Constants.ActionConstants;
import DTO.PostReportDTO;
import DTO.UserAccountDTO;
import DTO.UserHandlingDTO;

import com.vls.managementservice.model.*;
import com.vls.managementservice.service.AdminService;
import com.vls.managementservice.service.PostReportService;
import com.vls.managementservice.service.PostService;
import com.vls.managementservice.service.UserAccountService;
import com.vls.managementservice.service.UserHandlingService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RequestMapping("/admin")
@RestController
public class AdminController {
    private final AdminService adminService;
    private final ModelMapper modelMapper;
    private final PostReportService postReportService;
    private final PostService postService;
    private final UserAccountService userAccountService;
    private final UserHandlingService userHandlingService;

    @Autowired
    public AdminController(AdminService adminService, ModelMapper modelMapper, 
        PostReportService postReportService, PostService postService, UserAccountService userAccountService,
        UserHandlingService userHandlingService) {
        this.adminService = adminService;
        this.modelMapper = modelMapper;
        this.postReportService = postReportService;
        this.postService = postService;
        this.userAccountService = userAccountService;
        this.userHandlingService = userHandlingService;
    }

    @PostMapping("/login")
    public ResponseEntity<AdminAccount> login(@RequestBody AdminAccount adminAccount) {
        Optional<AdminAccount> admin = adminService.getAccount(adminAccount.getEmail());
        if(admin.isPresent()) {
            if(admin.get().getPwd().equalsIgnoreCase(adminAccount.getPwd())) {
                return new ResponseEntity<>(admin.get(), HttpStatus.ACCEPTED);
            }
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/list-report")
    public ResponseEntity<List<PostReportDTO>> hidePost() {
        List<PostReport> list = postReportService.getReports();
        List<PostReportDTO> result = new ArrayList<>();
        for (PostReport postReport : list) {
            result.add(modelMapper.map(postReport, PostReportDTO.class));
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/details-report/{reportId}")
    public ResponseEntity<PostReportDTO> details(@PathVariable("reportId") String reportId) {
        Optional<PostReport> reportDetail = postReportService.findPostReportById(UUID.fromString(reportId));
        if (reportDetail.isPresent()) {
            PostReportDTO postReportDTO = modelMapper.map(reportDetail.get(), PostReportDTO.class);
            return new ResponseEntity<>(postReportDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/handle-report", method = RequestMethod.POST)
    public ResponseEntity<PostReportDTO> hidePost(@RequestBody PostReportDTO postReportDTO) {
        PostReport postReport = modelMapper.map(postReportDTO, PostReport.class);         
        Optional<Post> post = postService.getPost(postReport.getPost().getId());
        if(postReportDTO.getHandling().equalsIgnoreCase(ActionConstants.PostHandling.DELETE_POST)) {
            postService.deletePost(post.get());
            postReportService.updatePostReport(postReport);
        } else {
            postService.hidePost(post.get());
            postReportService.updatePostReport(postReport);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/list-user")
    public ResponseEntity<List<UserAccountDTO>> listUser() {
        List<UserAccount> listUsers = userAccountService.getList();
        List<UserAccountDTO> result = new ArrayList<>();
        for (UserAccount userAccount : listUsers) {
            result.add(modelMapper.map(userAccount, UserAccountDTO.class));
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/user-handling/{userId}")
    public ResponseEntity<UserAccountDTO> getUserHandling(@PathVariable("userId") String userId) {
        Optional<UserAccount> userAccount = userAccountService.findUser(UUID.fromString(userId));
        if(userAccount.isPresent()) {
            List<UserHandling> userHandlings = userHandlingService.findUserHandlingByUserId(UUID.fromString(userId));
            List<UserHandlingDTO> userHandlingDTOList = new ArrayList<>();
            for (UserHandling user : userHandlings) {
                userHandlingDTOList.add(modelMapper.map(user, UserHandlingDTO.class));
            }
            UserAccount userData = userAccount.get();
            UserAccountDTO userAccountDTO = modelMapper.map(userData, UserAccountDTO.class);
            userAccountDTO.setUserHandlingList(userHandlingDTOList);
            return new ResponseEntity<>(userAccountDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/block-user")
    public ResponseEntity<UserHandlingDTO> blockUser(@RequestBody UserHandling userHandling) {
        userAccountService.block(userHandling.getUser_id());
        userHandling.setTime(new Date());
        UserHandling handling = userHandlingService.saveHandling(userHandling);
        UserHandlingDTO result = modelMapper.map(handling, UserHandlingDTO.class);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
