package com.ing.hubs.loan_api.service;

import com.ing.hubs.loan_api.entity.Customer;
import com.ing.hubs.loan_api.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {
    @Mock
    CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    @Test
    void customerHasLimitTest() {
        Customer customer = new Customer("John", "Doe", 5000.00, 0.00);
        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(customer));
        assertTrue(customerService.customerHasLimit(1L, 1000.0));
    }

    @Test
    void updateUsedCreditLimitTest() {
        Customer customer = new Customer("John", "Doe", 5000.00, 1000.0);
        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(customer));
        when(customerRepository.save(any())).thenReturn(customer);
        customerService.updateUsedCreditLimit(1L, 1000.0);
        assertEquals(0, customer.getUsedCreditLimit());
    }
}
