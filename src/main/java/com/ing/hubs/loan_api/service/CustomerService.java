package com.ing.hubs.loan_api.service;

import com.ing.hubs.loan_api.entity.Customer;
import com.ing.hubs.loan_api.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class CustomerService {
    private static final Logger LOGGER = Logger.getLogger(CustomerService.class.getName());
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public boolean customerHasLimit(long customerId, double loanAmount) {
        Customer customer = customerRepository.findById(customerId).get();
        return (customer.getCreditLimit() - customer.getUsedCreditLimit()) >= loanAmount;
    }

    void updateUsedCreditLimit(long customerId, double totalAmountSpent) {
        Customer customer = customerRepository.findById(customerId).get();
        double newUsedCreditLimit = customer.getUsedCreditLimit() - totalAmountSpent;
        customer.setUsedCreditLimit(newUsedCreditLimit);
        customerRepository.save(customer);
        LOGGER.info("Used credit limit is updated for customerId:" + customer.getId());
    }
}
