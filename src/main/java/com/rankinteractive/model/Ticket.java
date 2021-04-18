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
@Table(name = "tickets")
public class Ticket extends BaseEntity{

    @Column(unique = true, nullable = false)
    private String ticketCode;

    @Column(unique = true, nullable = false)
    private Integer price;

    @OneToOne(cascade = CascadeType.MERGE)
    private TonicEvent tonicEvent;
}
