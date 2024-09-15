package com.mbb.assessment.mapper;

import com.mbb.assessment.dto.TransactionDTO;
import com.mbb.assessment.entity.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {CustomerMapper.class})
public interface TransactionMapper {

    @Mapping(target = "customerId", source = "customer.id")
    @Mapping(target = ".", source = "customer")
    TransactionDTO toDto(Transaction entity);

    Transaction toEntity(TransactionDTO dto);
}
