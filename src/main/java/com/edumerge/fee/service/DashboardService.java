package com.edumerge.fee.service;

import com.edumerge.fee.dto.DashboardResponse;
import com.edumerge.fee.repository.PaymentRepository;
import com.edumerge.fee.repository.StudentFeeRepository;
import com.edumerge.fee.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class DashboardService {

    private final StudentRepository studentRepository;
    private final StudentFeeRepository studentFeeRepository;
    private final PaymentRepository paymentRepository;

    public DashboardService(
            StudentRepository studentRepository,
            StudentFeeRepository studentFeeRepository,
            PaymentRepository paymentRepository) {

        this.studentRepository = studentRepository;
        this.studentFeeRepository = studentFeeRepository;
        this.paymentRepository = paymentRepository;
    }

    public DashboardResponse getDashboard() {

        BigDecimal totalFee =
                studentFeeRepository.findAll()
                        .stream()
                        .map(fee -> fee.getTotalAmount())
                        .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalCollected =
                studentFeeRepository.findAll()
                        .stream()
                        .map(fee -> fee.getPaidAmount())
                        .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalDue =
                studentFeeRepository.findAll()
                        .stream()
                        .map(fee -> fee.getDueAmount())
                        .reduce(BigDecimal.ZERO, BigDecimal::add);

        long totalStudents =
                studentRepository.count();

        long totalPayments =
                paymentRepository.count();

        return new DashboardResponse(
                totalStudents,
                totalFee,
                totalCollected,
                totalDue,
                totalPayments
        );
    }
}