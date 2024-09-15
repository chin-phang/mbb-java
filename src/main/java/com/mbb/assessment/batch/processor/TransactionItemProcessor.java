package com.mbb.assessment.batch.processor;

import com.mbb.assessment.batch.record.TransactionRecord;
import com.mbb.assessment.dto.TransactionDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Slf4j
public class TransactionItemProcessor implements ItemProcessor<TransactionRecord, TransactionDTO> {

    private static final String LOG_PREFIX = TransactionItemProcessor.class.getName();

    @Override
    public TransactionDTO process(final TransactionRecord item) throws Exception {
        final String trxRefNo = UUID.randomUUID().toString();
        final String accountNo = item.accountNo();
        final BigDecimal trxAmount = item.trxAmount();
        final String description = item.description();
        final LocalDate trxDate = LocalDate.parse(item.trxDate());
        final LocalTime trxTime = LocalTime.parse(item.trxTime());
        final Long customerId = item.customerId();

        final TransactionDTO output = TransactionDTO.builder()
            .trxRefNo(trxRefNo)
            .accountNo(accountNo)
            .trxAmount(trxAmount)
            .description(description)
            .trxDate(trxDate)
            .trxTime(trxTime)
            .customerId(customerId)
            .build();

        log.info("{}Converting {} into {}", LOG_PREFIX, item, output);

        return output;
    }
}
