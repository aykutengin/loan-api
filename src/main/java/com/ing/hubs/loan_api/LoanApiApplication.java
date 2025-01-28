package com.ing.hubs.loan_api;

import com.ing.hubs.loan_api.entity.Customer;
import com.ing.hubs.loan_api.entity.Loan;
import com.ing.hubs.loan_api.entity.LoanInstallment;
import com.ing.hubs.loan_api.repository.CustomerRepository;
import com.ing.hubs.loan_api.repository.LoanInstallmentRepository;
import com.ing.hubs.loan_api.repository.LoanRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class LoanApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoanApiApplication.class, args);
    }


    @Bean
    public CommandLineRunner startup(CustomerRepository customerRepository, LoanRepository loanRepository, LoanInstallmentRepository loanInstallmentRepository) {
        return args -> {
            customerRepository.save(new Customer("John", "Doe", 5000.00, 500.00));
            customerRepository.save(new Customer("Jane", "Smith", 12000.00, 9000.00));
            customerRepository.save(new Customer("Michael", "Johnson", 10000.00, 3500.00));
            customerRepository.save(new Customer("Emily", "Davis", 6000.00, 0.00));
            customerRepository.save(new Customer("William", "Brown", 12000.00, 8800.00));

            loanRepository.save(new Loan(1, 3000.00, 6, LocalDate.parse("2024-01-01"), false));
            loanRepository.save(new Loan(2, 5000.00, 12, LocalDate.parse("2024-02-15"), false));
            loanRepository.save(new Loan(3, 8000.00, 24, LocalDate.parse("2024-03-10"), false));
            loanRepository.save(new Loan(4, 2500.00, 9, LocalDate.parse("2024-04-05"), true));
            loanRepository.save(new Loan(5, 10000.00, 12, LocalDate.parse("2024-05-20"), false));

            loanInstallmentRepository.save(new LoanInstallment(2, 416.67, 1000.00, LocalDate.parse("2024-03-15"), LocalDate.parse("2024-03-15"), true));

            loanInstallmentRepository.save(new LoanInstallment(1, 500.00, 500.00, LocalDate.of(2024, 2, 1), LocalDate.of(2024, 2, 1), true));
            loanInstallmentRepository.save(new LoanInstallment(1, 500.00, 500.00, LocalDate.of(2024, 3, 1), LocalDate.of(2024, 3, 1), true));
            loanInstallmentRepository.save(new LoanInstallment(1, 500.00, 500.00, LocalDate.of(2024, 4, 1), LocalDate.of(2024, 4, 1), true));
            loanInstallmentRepository.save(new LoanInstallment(1, 500.00, 500.00, LocalDate.of(2024, 5, 1), LocalDate.of(2024, 5, 1), true));
            loanInstallmentRepository.save(new LoanInstallment(1, 500.00, 500.00, LocalDate.of(2024, 6, 1), LocalDate.of(2024, 6, 1), true));
            loanInstallmentRepository.save(new LoanInstallment(1, 500.00, 0.00, LocalDate.of(2024, 7, 1), null, false));

            // Installments for Loan 2 (loanId = 2, 12 installments)
            loanInstallmentRepository.save(new LoanInstallment(2, 416.67, 416.67, LocalDate.of(2024, 3, 1), LocalDate.of(2024, 3, 1), true));
            loanInstallmentRepository.save(new LoanInstallment(2, 416.67, 416.67, LocalDate.of(2024, 4, 1), LocalDate.of(2024, 4, 1), true));
            loanInstallmentRepository.save(new LoanInstallment(2, 416.67, 416.67, LocalDate.of(2024, 5, 1), LocalDate.of(2024, 5, 1), true));
            loanInstallmentRepository.save(new LoanInstallment(2, 416.67, 0.00, LocalDate.of(2024, 6, 1), null, false));
            loanInstallmentRepository.save(new LoanInstallment(2, 416.67, 0.00, LocalDate.of(2024, 7, 1), null, false));
            loanInstallmentRepository.save(new LoanInstallment(2, 416.67, 0.00, LocalDate.of(2024, 8, 1), null, false));
            loanInstallmentRepository.save(new LoanInstallment(2, 416.67, 0.00, LocalDate.of(2024, 9, 1), null, false));
            loanInstallmentRepository.save(new LoanInstallment(2, 416.67, 0.00, LocalDate.of(2024, 10, 1), null, false));
            loanInstallmentRepository.save(new LoanInstallment(2, 416.67, 0.00, LocalDate.of(2024, 11, 1), null, false));
            loanInstallmentRepository.save(new LoanInstallment(2, 416.67, 0.00, LocalDate.of(2024, 12, 1), null, false));
            loanInstallmentRepository.save(new LoanInstallment(2, 416.67, 0.00, LocalDate.of(2025, 1, 1), null, false));
            loanInstallmentRepository.save(new LoanInstallment(2, 416.67, 0.00, LocalDate.of(2025, 2, 1), null, false));

            // Installments for Loan 3 (loanId = 3, 24 installments, partially included)
            loanInstallmentRepository.save(new LoanInstallment(3, 333.33, 333.33, LocalDate.of(2024, 4, 1), LocalDate.of(2024, 4, 10), true));
            loanInstallmentRepository.save(new LoanInstallment(3, 333.33, 333.33, LocalDate.of(2024, 5, 1), LocalDate.of(2024, 5, 10), true));
            loanInstallmentRepository.save(new LoanInstallment(3, 333.33, 0.00, LocalDate.of(2024, 6, 1), null, false));
            loanInstallmentRepository.save(new LoanInstallment(3, 333.33, 0.00, LocalDate.of(2024, 7, 1), null, false));
            loanInstallmentRepository.save(new LoanInstallment(3, 333.33, 0.00, LocalDate.of(2024, 8, 1), null, false));

            // Installments for Loan 4 (loanId = 4, 9 installments)
            loanInstallmentRepository.save(new LoanInstallment(4, 277.78, 277.78, LocalDate.of(2024, 5, 1), LocalDate.of(2024, 5, 5), true));
            loanInstallmentRepository.save(new LoanInstallment(4, 277.78, 277.78, LocalDate.of(2024, 6, 1), LocalDate.of(2024, 6, 5), true));
            loanInstallmentRepository.save(new LoanInstallment(4, 277.78, 277.78, LocalDate.of(2024, 7, 1), LocalDate.of(2024, 7, 5), true));
            loanInstallmentRepository.save(new LoanInstallment(4, 277.78, 277.78, LocalDate.of(2024, 8, 1), LocalDate.of(2024, 8, 5), true));
            loanInstallmentRepository.save(new LoanInstallment(4, 277.78, 277.78, LocalDate.of(2024, 9, 1), LocalDate.of(2024, 9, 5), true));
            loanInstallmentRepository.save(new LoanInstallment(4, 277.78, 277.78, LocalDate.of(2024, 10, 1), LocalDate.of(2024, 10, 5), true));
            loanInstallmentRepository.save(new LoanInstallment(4, 277.78, 277.78, LocalDate.of(2024, 11, 1), LocalDate.of(2024, 11, 5), true));
            loanInstallmentRepository.save(new LoanInstallment(4, 277.78, 277.78, LocalDate.of(2024, 12, 1), LocalDate.of(2024, 12, 5), true));
            loanInstallmentRepository.save(new LoanInstallment(4, 277.78, 277.78, LocalDate.of(2025, 1, 1), LocalDate.of(2025, 1, 5), true));

            // Installments for Loan 5 (loanId = 5, 12 installments)
            loanInstallmentRepository.save(new LoanInstallment(5, 833.33, 833.33, LocalDate.of(2024, 6, 1), LocalDate.of(2024, 6, 20), true));
            loanInstallmentRepository.save(new LoanInstallment(5, 833.33, 0.00, LocalDate.of(2024, 7, 1), null, false));
            loanInstallmentRepository.save(new LoanInstallment(5, 833.33, 0.00, LocalDate.of(2024, 8, 1), null, false));
            loanInstallmentRepository.save(new LoanInstallment(5, 833.33, 0.00, LocalDate.of(2024, 9, 1), null, false));
            loanInstallmentRepository.save(new LoanInstallment(5, 833.33, 0.00, LocalDate.of(2024, 10, 1), null, false));
            loanInstallmentRepository.save(new LoanInstallment(5, 833.33, 0.00, LocalDate.of(2024, 11, 1), null, false));
            loanInstallmentRepository.save(new LoanInstallment(5, 833.33, 0.00, LocalDate.of(2024, 12, 1), null, false));
            loanInstallmentRepository.save(new LoanInstallment(5, 833.33, 0.00, LocalDate.of(2025, 1, 1), null, false));
            loanInstallmentRepository.save(new LoanInstallment(5, 833.33, 0.00, LocalDate.of(2025, 2, 1), null, false));
            loanInstallmentRepository.save(new LoanInstallment(5, 833.33, 0.00, LocalDate.of(2025, 3, 1), null, false));
            loanInstallmentRepository.save(new LoanInstallment(5, 833.33, 0.00, LocalDate.of(2025, 4, 1), null, false));
            loanInstallmentRepository.save(new LoanInstallment(5, 833.33, 0.00, LocalDate.of(2025, 5, 1), null, false));
        };
    }
}

