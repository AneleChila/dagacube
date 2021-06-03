package com.rankinteractive.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "financial_accounts")
public class FinancialAccount extends BaseEntity {

    @Column(nullable = false)
    private int balanceAmount;

    @Column(nullable = true)
    private String bankName;

    @Column(nullable = true)
    private String bankAccountCardNumber;

    @Column(nullable = true)
    private int bankAccountCVV;

    @Column(nullable = true)
    private String exp;
}
