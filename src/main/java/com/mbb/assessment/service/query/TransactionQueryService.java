package com.mbb.assessment.service.query;

import com.cosium.spring.data.jpa.entity.graph.domain2.EntityGraph;
import com.mbb.assessment.dto.TransactionDTO;
import com.mbb.assessment.service.query.criteria.TransactionCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TransactionQueryService {

    Page<TransactionDTO> findAllByCriteria(TransactionCriteria criteria, Pageable pageable, EntityGraph entityGraph);
}
