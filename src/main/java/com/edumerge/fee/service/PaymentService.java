package com.edumerge.fee.service;

import com.edumerge.fee.entity.Payment;
import com.edumerge.fee.entity.StudentFee;
import com.edumerge.fee.repository.PaymentRepository;
import com.edumerge.fee.repository.StudentFeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final StudentFeeRepository studentFeeRepository;

    public PaymentService(
            PaymentRepository paymentRepository,
            StudentFeeRepository studentFeeRepository) {

        this.paymentRepository = paymentRepository;
        this.studentFeeRepository = studentFeeRepository;
    }

    @Transactional
    public Payment createPayment(Payment payment) {

        if (paymentRepository.existsByTransactionId(
                payment.getTransactionId())) {

            throw new RuntimeException(
                    "Transaction ID already exists"
            );
        }

        StudentFee studentFee =
                studentFeeRepository.findById(
                        payment.getStudentFee().getId()
                ).orElseThrow(() ->
                        new RuntimeException(
                                "Student fee not found"
                        ));

        BigDecimal paymentAmount = payment.getAmount();

        if (paymentAmount == null ||
                paymentAmount.compareTo(BigDecimal.ZERO) <= 0) {

            throw new RuntimeException(
                    "Payment amount must be greater than zero"
            );
        }

        if (paymentAmount.compareTo(
                studentFee.getDueAmount()) > 0) {

            throw new RuntimeException(
                    "Payment amount cannot be greater than due amount"
            );
        }

        if (payment.getStatus() == null) {
            payment.setStatus("SUCCESS");
        }

        if (payment.getStatus().equals("FAILED")) {

            payment.setStudentFee(studentFee);

            if (payment.getPaymentDate() == null) {
                payment.setPaymentDate(LocalDateTime.now());
            }

            return paymentRepository.save(payment);
        }

        BigDecimal newPaidAmount =
                studentFee.getPaidAmount()
                        .add(paymentAmount);

        BigDecimal newDueAmount =
                studentFee.getTotalAmount()
                        .subtract(newPaidAmount);

        studentFee.setPaidAmount(newPaidAmount);
        studentFee.setDueAmount(newDueAmount);

        if (newDueAmount.compareTo(BigDecimal.ZERO) == 0) {
            studentFee.setStatus("PAID");
        } else {
            studentFee.setStatus("PARTIAL");
        }

        payment.setStudentFee(studentFee);

        if (payment.getPaymentDate() == null) {
            payment.setPaymentDate(LocalDateTime.now());
        }

        studentFeeRepository.save(studentFee);

        return paymentRepository.save(payment);
    }

    @Transactional
    public Payment reversePayment(Long paymentId) {

        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() ->
                        new RuntimeException("Payment not found")
                );

        if ("REVERSED".equals(payment.getStatus())) {
            throw new RuntimeException(
                    "Payment is already reversed"
            );
        }

        if ("FAILED".equals(payment.getStatus())) {
            throw new RuntimeException(
                    "Failed payment cannot be reversed"
            );
        }

        StudentFee studentFee = payment.getStudentFee();

        BigDecimal newPaidAmount =
                studentFee.getPaidAmount()
                        .subtract(payment.getAmount());

        BigDecimal newDueAmount =
                studentFee.getTotalAmount()
                        .subtract(newPaidAmount);

        studentFee.setPaidAmount(newPaidAmount);
        studentFee.setDueAmount(newDueAmount);

        if (newPaidAmount.compareTo(BigDecimal.ZERO) == 0) {
            studentFee.setStatus("PENDING");
        } else {
            studentFee.setStatus("PARTIAL");
        }

        payment.setStatus("REVERSED");

        studentFeeRepository.save(studentFee);

        return paymentRepository.save(payment);
    }

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public Payment getPaymentById(Long id) {

        return paymentRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Payment not found"
                        ));
    }

    public List<Payment> getPaymentsByStudentFee(
            Long studentFeeId) {

        return paymentRepository
                .findByStudentFeeId(studentFeeId);
    }
}