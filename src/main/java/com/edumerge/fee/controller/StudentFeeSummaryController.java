package com.edumerge.fee.controller;

import com.edumerge.fee.dto.StudentFeeSummaryResponse;
import com.edumerge.fee.service.StudentFeeSummaryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/student-fee-summary")
public class StudentFeeSummaryController {

    private final StudentFeeSummaryService studentFeeSummaryService;

    public StudentFeeSummaryController(
            StudentFeeSummaryService studentFeeSummaryService) {

        this.studentFeeSummaryService = studentFeeSummaryService;
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<StudentFeeSummaryResponse> getSummary(
            @PathVariable Long studentId) {

        return ResponseEntity.ok(
                studentFeeSummaryService.getSummary(studentId)
        );
    }
}