package com.mbb.assessment.service.command.impl;

import com.mbb.assessment.dto.TransactionDTO;
import com.mbb.assessment.entity.Transaction;
import com.mbb.assessment.exception.TransactionNotFoundException;
import com.mbb.assessment.mapper.TransactionMapper;
import com.mbb.assessment.repository.TransactionRepository;
import com.mbb.assessment.service.command.TransactionCommandService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class TransactionCommandServiceImpl implements TransactionCommandService {

    private final TransactionRepository transactionRepository;

    private final TransactionMapper transactionMapper;

    @Override
    @Transactional
    public TransactionDTO update(TransactionDTO req) throws TransactionNotFoundException {
        Optional<Transaction> transactionOpt = transactionRepository.findByTrxRefNo(req.getTrxRefNo(), null);

        if (transactionOpt.isEmpty()) {
            String errMsg = String.format("Transaction not found - trxRefNo : %s", req.getTrxRefNo());
            log.error("update - message : {}", errMsg);
            throw new TransactionNotFoundException(errMsg);
        }

        Transaction dbTransaction = transactionOpt.get();
        dbTransaction.setDescription(req.getDescription());

        return transactionMapper.toDto(transactionRepository.save(dbTransaction));
    }
}
