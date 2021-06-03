package com.rankinteractive.response;


import com.rankinteractive.model.Customer;
import com.rankinteractive.model.FinancialAccount;
import com.rankinteractive.model.User;
import com.rankinteractive.request.CreateCustomerRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponse {

    private String emailAddress;
    private String firstName;
    private String lastName;
    private String idNumber;
    private String gender;
    private String mobileNUmber;

    public CustomerResponse(Customer customer) {
        this.firstName = customer.getFirstName();
        this.lastName = customer.getLastName();
        this.emailAddress = customer.getEmailAddress();
        this.idNumber = customer.getIdNumber();
        this.gender = customer.getGender();
        this.mobileNUmber = customer.getMobileNUmber();
    }


}
