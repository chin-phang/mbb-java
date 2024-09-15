package com.mbb.assessment.mapper;

import com.mbb.assessment.dto.CustomerDTO;
import com.mbb.assessment.entity.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {TransactionMapper.class})
public interface CustomerMapper {

    CustomerDTO toDto(Customer entity);

    Customer toEntity(CustomerDTO dto);
}
