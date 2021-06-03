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
@Table(name = "hosts")
public class Host extends User {

    @OneToOne(cascade = CascadeType.MERGE)
    private FinancialAccount financialAccount;
    @Column(unique = true, nullable = true)
    private String firstName;
    @Column(unique = true, nullable = true)
    private String lastName;
    @Column(unique = true, nullable = true)
    private String idNumber;
    @Column(unique = true, nullable = true)
    private String gender;
    @Column(unique = true, nullable = true)
    private String mobileNUmber;
    @Column(unique = true, nullable = true)
    private String hostType;
    @Column(unique = true, nullable = true)
    private String companyName;

}
