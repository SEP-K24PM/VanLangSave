package com.vls.managementservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.vls.managementservice.model.PostReport;
import com.vls.managementservice.repository.PostReportRepository;

import org.springframework.stereotype.Service;

@Service
public class PostReportService {
    private final PostReportRepository postReportRepository;

    public PostReportService(PostReportRepository postReportRepository) {
        this.postReportRepository = postReportRepository;
    }

    public List<PostReport> getReports() {
        List<PostReport> post_reports = new ArrayList<PostReport>();
        postReportRepository.findAll().forEach(post_reports::add);
        return post_reports;
    }

    public Optional<PostReport> findPostReportById(UUID id) {
        return postReportRepository.findById(id);
    }

    public PostReport updatePostReport(PostReport postReport) {
        return postReportRepository.save(postReport);
    }
}
