package com.vls.reportservice.service;

import com.vls.reportservice.model.Post_report;
import com.vls.reportservice.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReportService {

    private final ReportRepository reportRepository;

    @Autowired
    public ReportService(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    public List<Post_report> getAllReports() {
        List<Post_report> list = new ArrayList<Post_report>();
        reportRepository.findAll().forEach(list::add);
        return list;
    }

}
