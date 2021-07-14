package com.vls.adminreportmanagementservice.service;

import com.vls.adminreportmanagementservice.model.Post_Report;
import com.vls.adminreportmanagementservice.repository.PostreportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class  AdminReportService {

    private final PostreportRepository postreportRepository;

    @Autowired
    public AdminReportService(PostreportRepository postreportRepository) {
        this.postreportRepository = postreportRepository;
    }

    public List<Post_Report> getReports() {
        List<Post_Report> post_reports = new ArrayList<Post_Report>();
        postreportRepository.findAll().forEach(post_reports::add);
        return post_reports;
    }

    public Optional<Post_Report> findPostReportById(UUID id) {
        return postreportRepository.findById(id);
    }

    public Post_Report updatePostReport(Post_Report postReport) {
        return postreportRepository.save(postReport);
    }
}
