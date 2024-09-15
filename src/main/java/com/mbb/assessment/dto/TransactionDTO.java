package com.mbb.assessment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDTO {

    private String trxRefNo;

    private String accountNo;

    private BigDecimal trxAmount;

    private String description;

    private LocalDate trxDate;

    private LocalTime trxTime;

    private Long customerId;

    private CustomerDTO customer;
}
