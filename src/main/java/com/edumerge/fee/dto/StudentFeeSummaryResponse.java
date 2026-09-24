package com.edumerge.fee.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class StudentFeeSummaryResponse {

    private Long studentId;

    private BigDecimal totalFees;

    private BigDecimal totalPaid;

    private BigDecimal totalDue;

    private int feeRecords;
}