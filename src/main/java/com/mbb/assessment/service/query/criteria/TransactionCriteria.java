package com.mbb.assessment.service.query.criteria;

import lombok.Data;
import lombok.NoArgsConstructor;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.LongFilter;
import tech.jhipster.service.filter.StringFilter;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class TransactionCriteria implements Serializable, Criteria {

    @Serial
    private static final long serialVersionUID = -1891366760974901265L;

    private LongFilter customerId;

    private StringFilter accountNo;

    private StringFilter description;

    public TransactionCriteria(TransactionCriteria other) {
        this.customerId = other.customerId == null ? null : other.customerId.copy();
        this.accountNo = other.accountNo == null ? null : other.accountNo.copy();
        this.description = other.description == null ? null : other.description.copy();
    }

    @Override
    public TransactionCriteria copy() {
        return new TransactionCriteria(this);
    }
}
