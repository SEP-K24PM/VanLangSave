package com.vls.reportservice;

import com.vls.reportservice.controller.ReportController;
import com.vls.reportservice.model.PostReport;
import com.vls.reportservice.repository.PostReportRepository;
import com.vls.reportservice.service.PostReportService;

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
public class ReportControllerTest extends AbstractTest {
    private ReportController reportController;
    private PostReportService postReportService;
    @Mock
    private PostReportRepository postReportRepository;

    @Override
    @Before
    public void setUp() {
        super.setUp();
        postReportService = new PostReportService(postReportRepository);
        reportController = new ReportController(postReportService);
    }

    @Test
    public void createReport() {
        PostReport postReport = new PostReport("description", UUID.randomUUID(), UUID.randomUUID());
        PostReport savedPostReport = new PostReport(UUID.randomUUID(), "description", postReport.getPost_id(), postReport.getReporter_id());

        Mockito.when(postReportRepository.save(postReport)).thenReturn(savedPostReport);

        ResponseEntity<PostReport> repsponse = reportController.create(postReport);
        Assert.assertEquals(201, repsponse.getStatusCodeValue());
        Assert.assertEquals(savedPostReport, repsponse.getBody());
    }
}
