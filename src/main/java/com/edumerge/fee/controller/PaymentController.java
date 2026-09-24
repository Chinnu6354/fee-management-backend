package com.edumerge.fee.controller;

import com.edumerge.fee.entity.Payment;
import com.edumerge.fee.service.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(
            PaymentService paymentService) {

        this.paymentService = paymentService;
    }

    @PostMapping
    public ResponseEntity<Payment> createPayment(
            @RequestBody Payment payment) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(paymentService.createPayment(payment));
    }

    @GetMapping
    public ResponseEntity<List<Payment>> getAllPayments() {

        return ResponseEntity.ok(
                paymentService.getAllPayments()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Payment> getPaymentById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                paymentService.getPaymentById(id)
        );
    }

    @GetMapping("/student-fee/{studentFeeId}")
    public ResponseEntity<List<Payment>>
    getPaymentsByStudentFee(
            @PathVariable Long studentFeeId) {

        return ResponseEntity.ok(
                paymentService
                        .getPaymentsByStudentFee(studentFeeId)
        );
    }

    @PutMapping("/{id}/reverse")
    public ResponseEntity<Payment> reversePayment(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                paymentService.reversePayment(id)
        );
    }
}