package com.mbb.assessment.repository;

import com.cosium.spring.data.jpa.entity.graph.domain2.EntityGraph;
import com.cosium.spring.data.jpa.entity.graph.repository.EntityGraphJpaRepository;
import com.cosium.spring.data.jpa.entity.graph.repository.EntityGraphJpaSpecificationExecutor;
import com.mbb.assessment.entity.Transaction;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransactionRepository extends EntityGraphJpaRepository<Transaction, Long>,
    EntityGraphJpaSpecificationExecutor<Transaction> {

    Optional<Transaction> findByTrxRefNo(String trxRefNo, EntityGraph entityGraph);
}
