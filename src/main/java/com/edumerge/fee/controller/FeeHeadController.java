package com.edumerge.fee.controller;

import com.edumerge.fee.entity.FeeHead;
import com.edumerge.fee.service.FeeHeadService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/fee-heads")
public class FeeHeadController {

    private final FeeHeadService feeHeadService;

    public FeeHeadController(FeeHeadService feeHeadService) {
        this.feeHeadService = feeHeadService;
    }

    @PostMapping
    public ResponseEntity<FeeHead> createFeeHead(
            @RequestBody FeeHead feeHead) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(feeHeadService.createFeeHead(feeHead));
    }

    @GetMapping
    public ResponseEntity<List<FeeHead>> getAllFeeHeads() {

        return ResponseEntity.ok(
                feeHeadService.getAllFeeHeads()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<FeeHead> getFeeHeadById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                feeHeadService.getFeeHeadById(id)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<FeeHead> updateFeeHead(
            @PathVariable Long id,
            @RequestBody FeeHead feeHead) {

        return ResponseEntity.ok(
                feeHeadService.updateFeeHead(id, feeHead)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFeeHead(
            @PathVariable Long id) {

        feeHeadService.deleteFeeHead(id);

        return ResponseEntity.ok(
                "Fee head deleted successfully"
        );
    }
}