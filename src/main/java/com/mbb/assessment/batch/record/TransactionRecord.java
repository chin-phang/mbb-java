package com.mbb.assessment.batch.record;

import java.math.BigDecimal;

public record TransactionRecord(String accountNo, BigDecimal trxAmount, String description, String trxDate, String trxTime, Long customerId) {

}
