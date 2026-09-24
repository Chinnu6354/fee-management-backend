package com.edumerge.fee.service;

import com.edumerge.fee.dto.ReconciliationResponse;
import com.edumerge.fee.entity.StudentFee;
import com.edumerge.fee.repository.PaymentRepository;
import com.edumerge.fee.repository.StudentFeeRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ReconciliationService {

    private final PaymentRepository paymentRepository;
    private final StudentFeeRepository studentFeeRepository;

    public ReconciliationService(
            PaymentRepository paymentRepository,
            StudentFeeRepository studentFeeRepository) {

        this.paymentRepository = paymentRepository;
        this.studentFeeRepository = studentFeeRepository;
    }

    public ReconciliationResponse reconcile() {

        List<StudentFee> studentFees =
                studentFeeRepository.findAll();

        BigDecimal studentFeesPaidAmount =
                studentFees.stream()
                        .map(StudentFee::getPaidAmount)
                        .reduce(
                                BigDecimal.ZERO,
                                BigDecimal::add
                        );

        BigDecimal successfulPaymentsAmount =
                paymentRepository
                        .getSuccessfulPaymentsAmount();

        BigDecimal reversedPaymentsAmount =
                paymentRepository
                        .getReversedPaymentsAmount();

        BigDecimal failedPaymentsAmount =
                paymentRepository
                        .getFailedPaymentsAmount();

        BigDecimal difference =
                studentFeesPaidAmount
                        .subtract(successfulPaymentsAmount);

        boolean reconciled =
                difference.compareTo(BigDecimal.ZERO) == 0;

        return new ReconciliationResponse(
                studentFeesPaidAmount,
                successfulPaymentsAmount,
                reversedPaymentsAmount,
                failedPaymentsAmount,
                difference,
                reconciled
        );
    }
}