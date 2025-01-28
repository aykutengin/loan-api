package com.ing.hubs.loan_api.aspect;

import com.ing.hubs.loan_api.entity.Customer;
import com.ing.hubs.loan_api.repository.CustomerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import static com.ing.hubs.loan_api.config.SecurityConfiguration.ROLE_ADMIN;

@Aspect
@Component
public class SecurityAspect {
    private final CustomerRepository customerRepository;

    public SecurityAspect(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Before("execution(* com.ing.hubs.loan_api.service.LoanService.listLoans(..)) || execution(* com.ing.hubs.loan_api.service.CustomerService.*(..))")
    public void checkSecurityForLoan(JoinPoint joinPoint) {
        String customerIdStr = joinPoint.getArgs()[0].toString();
        if (!isUserAuthenticated(Long.valueOf(customerIdStr))) {
            throw new SecurityException("User not authenticated!");
        }
    }

    private boolean isUserAuthenticated(Long customerId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (ROLE_ADMIN.equals(authentication.getAuthorities().iterator().next().toString())) {
            return true;
        }
        User user = (User) authentication.getPrincipal();
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new EntityNotFoundException("Customer not found with ID: " + customerId));
        return user.getUsername().equals(customer.getName() + customer.getSurname());

    }
}
