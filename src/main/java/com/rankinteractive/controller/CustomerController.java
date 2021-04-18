package com.rankinteractive.controller;

import com.rankinteractive.exception.BadRequestException;
import com.rankinteractive.exception.InvalidRequestException;
import com.rankinteractive.exception.errors.ErrorCodes;
import com.rankinteractive.model.Customer;
import com.rankinteractive.request.CreateCustomerRequest;
import com.rankinteractive.request.UpdateCustomerRequest;
import com.rankinteractive.response.CustomerResponse;
import com.rankinteractive.service.CommonService;
import com.rankinteractive.service.CustomerService;
import com.rankinteractive.validator.CustomerValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.rankinteractive.exception.errors.ErrorCodes.INVALID_REQUEST;

/**
 * @author anelechila
 */
@RestController
@RequestMapping("/customers")
public class CustomerController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);

    private CustomerService customerService;
    private CustomerValidator customerValidator;
    private CommonService commonService;




    public CustomerController(CommonService commonService, CustomerValidator customerValidator, CustomerService customerService) {
        this.customerService = customerService;
        this.customerValidator = customerValidator;
        this.commonService = commonService;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public CustomerResponse  createCustomer(@RequestBody CreateCustomerRequest request,
                                            BindingResult bindingResult) {

        customerValidator.validate(request, bindingResult);

        if(bindingResult.hasErrors()) {
            throw new InvalidRequestException(INVALID_REQUEST.getResponseDesc(), bindingResult);
        }

        try {
            Customer customer = customerService.save(new Customer(request));
            return new CustomerResponse(customer);

        } catch (Exception e) {
             throw new BadRequestException(ErrorCodes.GENERAL_SYSTEM_ERR.getResponseDesc());
        }
    }

    @RequestMapping(value = "/{id}}", method = RequestMethod.GET)
    public CustomerResponse getCustomer(@PathVariable("id") Long id) {

        Customer cu = customerService.findById(id);
        if(cu != null) {
            return new CustomerResponse(cu);
        }

        throw new BadRequestException(ErrorCodes.CUSTOMER_NOT_FOUND.getResponseDesc());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT )
    public CustomerResponse updateCustomer(@PathVariable("id") Long id,
                                           @RequestBody UpdateCustomerRequest request, BindingResult bindingResult) {

        customerValidator.validateOnUpdate(request, bindingResult);

        if(bindingResult.hasErrors()) {
            throw new BadRequestException(INVALID_REQUEST.getResponseDesc());
        }

        return commonService.updateCustomer(request,id);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<CustomerResponse> getCustomers(@PathVariable("id") Long id) {

        return commonService.findAllCustomers();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public boolean deleteCustomer(@PathVariable("id") Long id) {

        commonService.deleteCustomer(id);

        return true;
    }







}