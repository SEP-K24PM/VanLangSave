package com.vls.adminreportmanagementservice;

import com.vls.adminreportmanagementservice.controller.AdminReportController;
import com.vls.adminreportmanagementservice.model.Post_Report;
import com.vls.adminreportmanagementservice.repository.PostreportRepository;
import com.vls.adminreportmanagementservice.service.AdminReportService;
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
import java.util.Optional;
import java.util.UUID;
import java.util.List;

@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)

public class AdminReportControllerTest extends AbstractTest {
    private AdminReportController adminReportController;
    private AdminReportService adminReportService;

    @Mock
    private PostreportRepository postreportRepository;

    @Override
    @Before
    public void setUp() {
        super.setUp();
        adminReportService = new AdminReportService(postreportRepository);
        adminReportController = new AdminReportController(adminReportService);
    }

    @Test
    public void adminReport() {
        Post_Report post_report = new Post_Report("mo ta2", UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID(), "abc");
        List<Post_Report> post_reports = new ArrayList<>();
        post_reports.add(new Post_Report("mo ta", UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID(), "abc"));

        Mockito.when(postreportRepository.findAll()).thenReturn(post_reports);
        Mockito.when(postreportRepository.findById(post_report.getId())).thenReturn(Optional.of(post_report));

        ResponseEntity<List<Post_Report>> response1 = adminReportController.getAllReports();
        Assert.assertEquals(200, response1.getStatusCodeValue());
        Assert.assertEquals(post_reports, response1.getBody());

        ResponseEntity<Post_Report> response = adminReportController.getReportDetail(post_report.getId());
        Assert.assertEquals(200, response.getStatusCodeValue());
        Assert.assertEquals(post_report, response.getBody());

        Mockito.when(postreportRepository.findById(post_report.getId())).thenReturn(Optional.empty());
        ResponseEntity<Post_Report> responseNotFound1 = adminReportController.getReportDetail(post_report.getId());
        Assert.assertEquals(404, responseNotFound1.getStatusCodeValue());
    }

}
