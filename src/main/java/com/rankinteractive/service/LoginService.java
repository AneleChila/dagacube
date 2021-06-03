package com.rankinteractive.service;

import com.rankinteractive.model.Customer;
import com.rankinteractive.model.SessionStatus;
import com.rankinteractive.model.TonicEvent;
import com.rankinteractive.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginService {

    private CustomerService customerService;
    private EventService eventService;

    public LoginService(CustomerService customerService, EventService eventService) {
        this.customerService = customerService;
        this.eventService = eventService;
    }

    public List<TonicEvent> login(String emailAddress, String password) {
        Customer customer = customerService.getCustomerByEmailAndUsername(emailAddress, password);
        customer.setSessionStatus(SessionStatus.ONLINE.getStatus());

        customerService.save(customer);

        //get latest count = 10 events
        return eventService.findAll();

    }

    public void login(Long id) {
        Customer customer = customerService.findById(id);
        customer.setSessionStatus(SessionStatus.ONLINE.getStatus());

        customerService.save(customer);
    }
}
