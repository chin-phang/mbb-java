package com.mbb.assessment.entity;

import com.mbb.assessment.enumeration.AccountType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "account")
public class Account extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_type", length = 50, nullable = false)
    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    @Column(name = "account_no", length = 100, nullable = false, unique = true)
    private String accountNo;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn
    private Customer customer;
}
