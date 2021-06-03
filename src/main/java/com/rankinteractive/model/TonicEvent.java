package com.rankinteractive.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "events")
public class TonicEvent extends BaseEntity {

    @Column(unique = true, nullable = false)
    private String location;
    @Column(unique = true, nullable = false)
    private String imageURL;
    @Column(unique = true, nullable = false)
    private Timestamp startDate;
    @Column(unique = true, nullable = false)
    private Timestamp endDate;
}
