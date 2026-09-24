package com.edumerge.fee.controller;

import com.edumerge.fee.entity.StudentFee;
import com.edumerge.fee.service.StudentFeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/admin/student-fees")
public class StudentFeeController {

    private final StudentFeeService studentFeeService;

    public StudentFeeController(
            StudentFeeService studentFeeService) {

        this.studentFeeService = studentFeeService;
    }

    @PostMapping
    public ResponseEntity<StudentFee> create(
            @Valid @RequestBody StudentFee studentFee) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        studentFeeService
                                .createStudentFee(studentFee)
                );
    }

    @GetMapping
    public ResponseEntity<List<StudentFee>> getAll() {

        return ResponseEntity.ok(
                studentFeeService.getAllStudentFees()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentFee> getById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                studentFeeService.getStudentFeeById(id)
        );
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<StudentFee>> getByStudent(
            @PathVariable Long studentId) {

        return ResponseEntity.ok(
                studentFeeService
                        .getFeesByStudent(studentId)
        );
    }
}