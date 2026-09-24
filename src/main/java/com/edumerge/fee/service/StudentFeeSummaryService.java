package com.edumerge.fee.service;

import com.edumerge.fee.dto.StudentFeeSummaryResponse;
import com.edumerge.fee.entity.StudentFee;
import com.edumerge.fee.repository.StudentFeeRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class StudentFeeSummaryService {

    private final StudentFeeRepository studentFeeRepository;

    public StudentFeeSummaryService(
            StudentFeeRepository studentFeeRepository) {

        this.studentFeeRepository = studentFeeRepository;
    }

    public StudentFeeSummaryResponse getSummary(Long studentId) {

        List<StudentFee> fees =
                studentFeeRepository.findByStudentId(studentId);

        if (fees.isEmpty()) {
            throw new RuntimeException(
                    "No fee records found for student"
            );
        }

        BigDecimal totalFees =
                fees.stream()
                        .map(StudentFee::getTotalAmount)
                        .reduce(
                                BigDecimal.ZERO,
                                BigDecimal::add
                        );

        BigDecimal totalPaid =
                fees.stream()
                        .map(StudentFee::getPaidAmount)
                        .reduce(
                                BigDecimal.ZERO,
                                BigDecimal::add
                        );

        BigDecimal totalDue =
                fees.stream()
                        .map(StudentFee::getDueAmount)
                        .reduce(
                                BigDecimal.ZERO,
                                BigDecimal::add
                        );

        return new StudentFeeSummaryResponse(
                studentId,
                totalFees,
                totalPaid,
                totalDue,
                fees.size()
        );
    }
}