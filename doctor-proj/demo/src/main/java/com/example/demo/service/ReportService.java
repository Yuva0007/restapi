package com.example.demo.service;

import com.example.demo.entity.Report;
import com.example.demo.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ReportService {

    @Autowired
    private ReportRepository reportRepository;

    public List<Report> getAllReports() {
        return reportRepository.findAll();
    }

    public Optional<Report> getReportById(Long id) {
        return reportRepository.findById(id);
    }

    public List<Report> getReportsByDoctorId(Long doctorId) {
        return reportRepository.findByDoctorId(doctorId);
    }

    public List<Report> getReportsByPatientId(Long patientId) {
        return reportRepository.findByPatientId(patientId);
    }

    public Report createReport(Report report) {
        report.setGeneratedDate(LocalDate.now());
        return reportRepository.save(report);
    }

    public Report updateReport(Long id, Report reportDetails) {
        return reportRepository.findById(id).map(report -> {
            report.setReportType(reportDetails.getReportType());
            report.setDescription(reportDetails.getDescription());
            report.setDoctor(reportDetails.getDoctor());
            report.setPatient(reportDetails.getPatient());
            return reportRepository.save(report);
        }).orElseThrow(() -> new RuntimeException("Report not found with id: " + id));
    }

    public void deleteReport(Long id) {
        reportRepository.deleteById(id);
    }
}
