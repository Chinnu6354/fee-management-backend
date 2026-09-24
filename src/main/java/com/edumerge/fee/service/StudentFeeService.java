package com.edumerge.fee.service;

import com.edumerge.fee.entity.StudentFee;
import com.edumerge.fee.repository.StudentFeeRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class StudentFeeService {

    private final StudentFeeRepository studentFeeRepository;

    public StudentFeeService(
            StudentFeeRepository studentFeeRepository) {

        this.studentFeeRepository = studentFeeRepository;
    }

    public StudentFee createStudentFee(
            StudentFee studentFee) {

        BigDecimal total = studentFee.getTotalAmount();

        if (total == null) {
            throw new RuntimeException(
                    "Total amount is required"
            );
        }

        BigDecimal discount = studentFee.getDiscountAmount();

        if (discount == null) {
            discount = BigDecimal.ZERO;
            studentFee.setDiscountAmount(discount);
        }

        // Discount cannot be negative
        if (discount.compareTo(BigDecimal.ZERO) < 0) {
            throw new RuntimeException(
                    "Discount cannot be negative"
            );
        }

        // Discount cannot be greater than original fee
        if (discount.compareTo(total) > 0) {
            throw new RuntimeException(
                    "Discount cannot be greater than total amount"
            );
        }

        // Calculate final payable amount
        BigDecimal finalAmount =
                total.subtract(discount);

        studentFee.setFinalAmount(finalAmount);

        BigDecimal paid = studentFee.getPaidAmount();

        if (paid == null) {
            paid = BigDecimal.ZERO;
            studentFee.setPaidAmount(paid);
        }

        // Paid amount cannot be negative
        if (paid.compareTo(BigDecimal.ZERO) < 0) {
            throw new RuntimeException(
                    "Paid amount cannot be negative"
            );
        }

        // Paid amount cannot be greater than final amount
        if (paid.compareTo(finalAmount) > 0) {
            throw new RuntimeException(
                    "Paid amount cannot be greater than final amount"
            );
        }

        // Calculate due amount
        BigDecimal due =
                finalAmount.subtract(paid);

        studentFee.setDueAmount(due);

        // Calculate status
        if (paid.compareTo(BigDecimal.ZERO) == 0) {

            studentFee.setStatus("PENDING");

        } else if (due.compareTo(BigDecimal.ZERO) == 0) {

            studentFee.setStatus("PAID");

        } else {

            studentFee.setStatus("PARTIAL");
        }

        return studentFeeRepository.save(studentFee);
    }

    public List<StudentFee> getAllStudentFees() {

        return studentFeeRepository.findAll();
    }

    public StudentFee getStudentFeeById(Long id) {

        return studentFeeRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Student fee not found"
                        ));
    }

    public List<StudentFee> getFeesByStudent(
            Long studentId) {

        return studentFeeRepository
                .findByStudentId(studentId);
    }
}