package com.rankinteractive.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name ="promotions")
public class Promotion extends BaseEntity {


    @Column(nullable = false, unique = true)
    private boolean isActive;

    @Column(nullable = false, unique = true)
    private int availableWagers;


}
