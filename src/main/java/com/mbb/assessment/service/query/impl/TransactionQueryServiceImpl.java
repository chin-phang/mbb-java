package com.mbb.assessment.service.query.impl;

import com.cosium.spring.data.jpa.entity.graph.domain2.EntityGraph;
import com.mbb.assessment.dto.TransactionDTO;
import com.mbb.assessment.entity.Customer_;
import com.mbb.assessment.entity.Transaction;
import com.mbb.assessment.entity.Transaction_;
import com.mbb.assessment.mapper.TransactionMapper;
import com.mbb.assessment.repository.TransactionRepository;
import com.mbb.assessment.service.command.TransactionCommandService;
import com.mbb.assessment.service.query.TransactionQueryService;
import com.mbb.assessment.service.query.criteria.TransactionCriteria;
import jakarta.persistence.criteria.JoinType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TransactionQueryServiceImpl extends QueryService<Transaction> implements TransactionQueryService {

    private final TransactionRepository transactionRepository;

    private final TransactionMapper transactionMapper;

    @Override
    public Page<TransactionDTO> findAllByCriteria(TransactionCriteria criteria, Pageable pageable, EntityGraph entityGraph) {
        log.debug("findAllByCriteria - criteria : {} | pageable : {} | entityGraph : {}", criteria, pageable, entityGraph);

        final Specification<Transaction> specification = createSpecification(criteria);

        Page<Transaction> result = transactionRepository.findAll(specification, pageable, entityGraph);
        List<TransactionDTO> content = result.getContent().stream()
            .map(transactionMapper::toDto).toList();

        return new PageImpl<>(content, pageable, result.getTotalElements());
    }

    private Specification<Transaction> createSpecification(TransactionCriteria criteria) {
        Specification<Transaction> specification = Specification.where(null);

        if (criteria != null) {
            if (criteria.getCustomerId() != null) {
                specification = specification.and(buildSpecification(criteria.getCustomerId(),
                    root -> root.join(Transaction_.customer, JoinType.LEFT).get(Customer_.id)));
            }

            if (criteria.getAccountNo() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAccountNo(), Transaction_.accountNo));
            }

            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), Transaction_.description));
            }
        }

        return specification;
    }
}
