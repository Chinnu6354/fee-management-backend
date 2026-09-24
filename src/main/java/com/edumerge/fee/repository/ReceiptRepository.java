package com.edumerge.fee.repository;

import com.edumerge.fee.entity.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReceiptRepository
        extends JpaRepository<Receipt, Long> {

    Optional<Receipt> findByPaymentId(Long paymentId);

    boolean existsByReceiptNumber(String receiptNumber);
}