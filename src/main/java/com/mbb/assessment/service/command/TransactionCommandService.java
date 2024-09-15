package com.mbb.assessment.service.command;

import com.mbb.assessment.dto.TransactionDTO;
import com.mbb.assessment.exception.TransactionNotFoundException;

public interface TransactionCommandService {

    TransactionDTO update(TransactionDTO req) throws TransactionNotFoundException;
}
