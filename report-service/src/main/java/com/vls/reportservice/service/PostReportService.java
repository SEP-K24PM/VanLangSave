package com.vls.reportservice.service;

import com.vls.reportservice.model.PostReport;
import com.vls.reportservice.repository.PostReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostReportService {
    private final PostReportRepository postReportRepository;

    @Autowired
    public PostReportService(PostReportRepository postReportRepository) {
        this.postReportRepository = postReportRepository;
    }

    public PostReport createReport(PostReport postReport) {
        return postReportRepository.save(postReport);
    }
}
