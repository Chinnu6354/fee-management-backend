package com.edumerge.fee.controller;

import com.edumerge.fee.entity.Receipt;
import com.edumerge.fee.service.ReceiptService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/receipts")
public class ReceiptController {

    private final ReceiptService receiptService;

    public ReceiptController(
            ReceiptService receiptService) {

        this.receiptService = receiptService;
    }

    @PostMapping("/payment/{paymentId}")
    public ResponseEntity<Receipt> createReceipt(
            @PathVariable Long paymentId) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        receiptService
                                .createReceipt(paymentId)
                );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Receipt> getReceiptById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                receiptService.getReceiptById(id)
        );
    }

    @GetMapping("/payment/{paymentId}")
    public ResponseEntity<Receipt> getReceiptByPaymentId(
            @PathVariable Long paymentId) {

        return ResponseEntity.ok(
                receiptService
                        .getReceiptByPaymentId(paymentId)
        );
    }
}