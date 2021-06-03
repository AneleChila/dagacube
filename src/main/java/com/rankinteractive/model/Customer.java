package com.rankinteractive.model;


import com.rankinteractive.request.CreateCustomerRequest;
import com.rankinteractive.request.UpdateCustomerRequest;
import lombok.*;
import sun.java2d.cmm.Profile;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customers")
public class Customer extends User {

    @OneToOne(cascade = CascadeType.MERGE)
    private FinancialAccount financialAccount;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(unique = true, nullable = true)
    private String idNumber;
    @Column(nullable = true)
    private String gender;
    @Column(nullable = true)
    private String mobileNUmber;

    @Column(nullable = false)
    private String sessionStatus;



    public Customer(CreateCustomerRequest request) {
        super(request.getEmailAddress(), request.getPassword());
        this.firstName = request.getFirstName();
        this.lastName = request.getLastName();
    }

    public void updateCustomer(Long id, UpdateCustomerRequest request) {
        this.firstName = request.getFirstName() == null ? this.firstName : request.getEmailAddress();
        this.lastName = request.getLastName() == null ? this.lastName : request.getLastName();
        this.idNumber = request.getIdNumber() == null ? this.idNumber : request.getIdNumber();
        this.gender = request.getGender() == null ? this.gender : request.getGender();
        this.mobileNUmber = request.getMobileNUmber() == null ? this.mobileNUmber : request.getMobileNUmber();
        this.setId(id);
    }


}
