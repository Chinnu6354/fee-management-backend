package com.edumerge.fee.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class DashboardResponse {

    private long totalStudents;

    private BigDecimal totalFee;

    private BigDecimal totalCollected;

    private BigDecimal totalDue;

    private long totalPayments;
}