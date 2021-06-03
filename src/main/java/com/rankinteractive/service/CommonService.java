package com.rankinteractive.service;

import com.rankinteractive.exception.AuthenticationException;
import com.rankinteractive.exception.BadRequestException;
import com.rankinteractive.exception.errors.ErrorCodes;
import com.rankinteractive.model.*;
import com.rankinteractive.request.UpdateCustomerRequest;
import com.rankinteractive.response.CustomerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CommonService {

    private CustomerService customerService;

    @Autowired
    public CommonService(CustomerService customerService) {
        this.customerService = customerService;
    }

    public CustomerResponse updateCustomer(UpdateCustomerRequest request, Long id) {
       Customer customer = getAndValidateCustomer(id);

       customer.updateCustomer(id,request);

       Customer savedCustomer = customerService.save(customer);


       return new CustomerResponse(savedCustomer);

    }

    private Customer getAndValidateCustomer(Long id) {
        Customer customer = customerService.findById(id);

        if(customer == null) {
            throw new BadRequestException(ErrorCodes.CUSTOMER_NOT_FOUND.getResponseDesc());
        }

        if(customer.getSessionStatus().equals(SessionStatus.OFFLINE.getStatus())) {
            throw new AuthenticationException(ErrorCodes.AUTHENTICATION_FAILED.getResponseDesc());
        }
        return customer;
    }

    public List<CustomerResponse> findAllCustomers() {

        List<CustomerResponse> customerResponse = new ArrayList<>();
        List<Customer> customers = customerService.findAll();

        customers.forEach( customer -> {
            customerResponse.add(new CustomerResponse(customer));
                });

        return customerResponse;

    }

    public boolean deleteCustomer(Long id) {
        Customer customer = getAndValidateCustomer(id);

        customerService.deleteById(id);

        return true;
    }




}
