package com.edumerge.fee.repository;

import com.edumerge.fee.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface PaymentRepository
        extends JpaRepository<Payment, Long> {

    List<Payment> findByStudentFeeId(Long studentFeeId);

    boolean existsByTransactionId(String transactionId);

    @Query("""
            SELECT COALESCE(SUM(p.amount), 0)
            FROM Payment p
            WHERE p.status = 'SUCCESS'
            """)
    BigDecimal getSuccessfulPaymentsAmount();

    @Query("""
            SELECT COALESCE(SUM(p.amount), 0)
            FROM Payment p
            WHERE p.status = 'REVERSED'
            """)
    BigDecimal getReversedPaymentsAmount();

    @Query("""
            SELECT COALESCE(SUM(p.amount), 0)
            FROM Payment p
            WHERE p.status = 'FAILED'
            """)
    BigDecimal getFailedPaymentsAmount();
}