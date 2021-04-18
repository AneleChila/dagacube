package com.rankinteractive.service;

import com.rankinteractive.config.DagacubeLogger;
import com.rankinteractive.exception.AuthenticationException;
import com.rankinteractive.exception.BadRequestException;
import com.rankinteractive.exception.InternalServerErrorException;
import com.rankinteractive.exception.errors.ErrorCodes;
import com.rankinteractive.model.Customer;
import com.rankinteractive.model.SessionStatus;
import com.rankinteractive.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    @DagacubeLogger
    public List<Customer> findAll() {
        try {
            return customerRepository.findAll();
        } catch (Exception e) {
            throw new InternalServerErrorException(ErrorCodes.GENERAL_DATABASE_ERR.getResponseDesc());
        }
    }

    @Override
    @DagacubeLogger
    public Customer findById(Long id) {

        try {
           Optional<Customer> optionalCustomer = customerRepository.findById(id);

            return optionalCustomer.orElse(null);

        } catch (Exception e) {
            throw new InternalServerErrorException(ErrorCodes.GENERAL_DATABASE_ERR.getResponseDesc());
        }
    }

    @Override
    @DagacubeLogger
    public Customer save(Customer customer) {

        try {
            if(customer.getId() == null) {
                customer.setCreateDate(new Date());
            }
            customer.setLastModifiedDate(new Date());

            return customerRepository.save(customer);
        } catch (Exception e) {
            throw new InternalServerErrorException(ErrorCodes.GENERAL_DATABASE_ERR.getResponseDesc());
        }

    }

    @Override
    @DagacubeLogger
    public void delete(Customer customer){
        try {
            customerRepository.delete(customer);
        } catch (Exception e) {
            throw new InternalServerErrorException(ErrorCodes.GENERAL_DATABASE_ERR.getResponseDesc());
        }
    }

    @Override
    @DagacubeLogger
    public void deleteById(Long id){
        try {
            customerRepository.deleteById(id);
        } catch (Exception e) {
            throw new InternalServerErrorException(ErrorCodes.GENERAL_DATABASE_ERR.getResponseDesc());
        }
    }

    public Customer getCustomerByEmailAndUsername(String emailAddress, String password) {

        Customer customer = customerRepository.findByCredentials(emailAddress, password);

        if(customer == null) {
            throw new AuthenticationException(ErrorCodes.AUTHENTICATION_FAILED.getResponseDesc());
        }

        if(customer.getSessionStatus().equals(SessionStatus.ONLINE)) {
            //push notification to alert login activity on a different device
        }

        return customer;

    }
}
