package com.edumerge.fee.controller;

import com.edumerge.fee.entity.StudentFee;
import com.edumerge.fee.service.OutstandingFeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/outstanding-fees")
public class OutstandingFeeController {

    private final OutstandingFeeService outstandingFeeService;

    public OutstandingFeeController(
            OutstandingFeeService outstandingFeeService) {

        this.outstandingFeeService = outstandingFeeService;
    }

    @GetMapping
    public ResponseEntity<List<StudentFee>> getOutstandingFees() {

        return ResponseEntity.ok(
                outstandingFeeService.getOutstandingFees()
        );
    }
}