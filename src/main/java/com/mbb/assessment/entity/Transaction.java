package com.mbb.assessment.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "mbb_transaction")
@NamedEntityGraphs(value = {
    @NamedEntityGraph(name = "transactionCustomer", attributeNodes = {
        @NamedAttributeNode("customer")
    })
})
public class Transaction extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "trx_reference_no", length = 100, nullable = false, unique = true)
    private String trxRefNo;

    @Column(name = "account_no", length = 100, nullable = false)
    private String accountNo;

    @Column(name = "trx_amount", precision = 20, scale = 2, nullable = false)
    private BigDecimal trxAmount;

    @Column(name = "description", length = 200, nullable = false)
    private String description;

    @Column(name = "trx_date", nullable = false)
    private LocalDate trxDate;

    @Column(name = "trx_time", nullable = false)
    private LocalTime trxTime;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn
    private Customer customer;
}
