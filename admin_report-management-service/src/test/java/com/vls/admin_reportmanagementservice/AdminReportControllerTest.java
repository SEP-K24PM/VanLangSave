package com.vls.admin_reportmanagementservice;

import com.vls.adminreportmanagementservice.controller.AdminReportController;
import com.vls.adminreportmanagementservice.model.Post_report;
import com.vls.adminreportmanagementservice.repository.PostreportRepository;
import com.vls.adminreportmanagementservice.repository.AdminreportRepository;
import com.vls.adminreportmanagementservice.service.AdminReportService;
import org.checkerframework.checker.units.qual.C;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.UUID;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)

public class AdminReportControllerTest extends AbstractTest {

    private AdminReportController adminReportController;
    private AdminReportService adminReportService;

    @Mock
    private AdminreportRepository adminreportRepository;

    @Mock
    private PostreportRepository postreportRepository;

    @Override
    @Before
    public void setUp() {
        super.setUp();
        adminReportService = new AdminReportService(adminreportRepository, postreportRepository);
        adminReportController = new AdminReportController(adminReportService);
    }

    @Test
    public void adminReport() {
        Post_report post_report = new Post_report("mo ta2", UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID(), "abc");
        List<Post_report> post_reports = new ArrayList<>();
        post_reports.add(new Post_report("mo ta", UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID(), "abc"));

        Mockito.when(adminreportRepository.findAll()).thenReturn(post_reports);
        Mockito.when(postreportRepository.findPost_reportById(post_report.getId())).thenReturn(post_report);

        ResponseEntity<List<Post_report>> response1 = adminReportController.getAllReports();
        Assert.assertEquals(200, response1.getStatusCodeValue());
        Assert.assertEquals(true, response1.getBody());

        ResponseEntity<Post_report> response = adminReportController.getReportDetail(post_report.getId());
        Assert.assertEquals(200, response.getStatusCodeValue());
        Assert.assertEquals(true, response.getBody());

        Mockito.doNothing().when(adminreportRepository).findAll();
        ResponseEntity<List<Post_report>> responseNotFound = adminReportController.getAllReports();
        Assert.assertEquals(404, responseNotFound.getStatusCodeValue());

        Mockito.doNothing().when(postreportRepository).findPost_reportById(post_report.getId());
        ResponseEntity<Post_report> responseNotFound1 = adminReportController.getReportDetail(post_report.getId());
        Assert.assertEquals(404, responseNotFound1.getStatusCodeValue());


    }

}
