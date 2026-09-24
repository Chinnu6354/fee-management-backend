package com.edumerge.fee.service;

import com.edumerge.fee.entity.Payment;
import com.edumerge.fee.entity.Receipt;
import com.edumerge.fee.repository.PaymentRepository;
import com.edumerge.fee.repository.ReceiptRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ReceiptService {

    private final ReceiptRepository receiptRepository;
    private final PaymentRepository paymentRepository;

    public ReceiptService(
            ReceiptRepository receiptRepository,
            PaymentRepository paymentRepository) {

        this.receiptRepository = receiptRepository;
        this.paymentRepository = paymentRepository;
    }

    public Receipt createReceipt(Long paymentId) {

        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() ->
                        new RuntimeException("Payment not found"));

        if (receiptRepository.findByPaymentId(paymentId).isPresent()) {
            throw new RuntimeException(
                    "Receipt already exists for this payment"
            );
        }

        Receipt receipt = Receipt.builder()
                .receiptNumber(generateReceiptNumber())
                .payment(payment)
                .generatedAt(LocalDateTime.now())
                .build();

        return receiptRepository.save(receipt);
    }

    public Receipt getReceiptById(Long id) {

        return receiptRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Receipt not found"));
    }

    public Receipt getReceiptByPaymentId(Long paymentId) {

        return receiptRepository.findByPaymentId(paymentId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Receipt not found for this payment"
                        ));
    }

    private String generateReceiptNumber() {

        return "REC-" +
                String.format(
                        "%05d",
                        receiptRepository.count() + 1
                );
    }
}