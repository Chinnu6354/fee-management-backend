package com.edumerge.fee.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class ReconciliationResponse {

    private BigDecimal studentFeesPaidAmount;

    private BigDecimal successfulPaymentsAmount;

    private BigDecimal reversedPaymentsAmount;

    private BigDecimal failedPaymentsAmount;

    private BigDecimal difference;

    private boolean reconciled;
}