package com.edumerge.fee.repository;

import com.edumerge.fee.entity.StudentFee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface StudentFeeRepository
        extends JpaRepository<StudentFee, Long> {

    List<StudentFee> findByStudentId(Long studentId);

    List<StudentFee> findByDueAmountGreaterThan(BigDecimal amount);
}