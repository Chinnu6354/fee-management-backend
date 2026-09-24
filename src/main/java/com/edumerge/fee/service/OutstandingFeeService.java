package com.edumerge.fee.service;

import com.edumerge.fee.entity.StudentFee;
import com.edumerge.fee.repository.StudentFeeRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OutstandingFeeService {

    private final StudentFeeRepository studentFeeRepository;

    public OutstandingFeeService(
            StudentFeeRepository studentFeeRepository) {

        this.studentFeeRepository = studentFeeRepository;
    }

    public List<StudentFee> getOutstandingFees() {

        return studentFeeRepository
                .findByDueAmountGreaterThan(BigDecimal.ZERO);
    }
}