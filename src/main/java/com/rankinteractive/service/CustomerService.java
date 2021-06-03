package com.rankinteractive.service;

import com.rankinteractive.model.Customer;
import org.springframework.stereotype.Service;

public interface CustomerService extends CrudService<Customer, Long> {

    Customer getCustomerByEmailAndUsername(String emailAddress, String password);
}
