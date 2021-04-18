package com.rankinteractive.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "transactions")
public class Transaction extends BaseEntity {

    @Column(unique = true, nullable = false)
    private Long customerId;

    @Column(unique = true, nullable = false)
    private Long transactionId;

    @Column(unique = true, nullable = false)
    private String transactionType;

}
