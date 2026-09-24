package com.edumerge.fee.controller;

import com.edumerge.fee.dto.ReconciliationResponse;
import com.edumerge.fee.service.ReconciliationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/reconciliation")
public class ReconciliationController {

    private final ReconciliationService reconciliationService;

    public ReconciliationController(
            ReconciliationService reconciliationService) {

        this.reconciliationService = reconciliationService;
    }

    @GetMapping
    public ResponseEntity<ReconciliationResponse> reconcile() {

        return ResponseEntity.ok(
                reconciliationService.reconcile()
        );
    }
}