package com.ing.hubs.loan_api.service;

import com.ing.hubs.loan_api.dto.LoanDTO;
import com.ing.hubs.loan_api.entity.Loan;
import com.ing.hubs.loan_api.repository.LoanRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

@Service
public class LoanService {
    private static final Logger LOGGER = Logger.getLogger(LoanService.class.getName());

    private final Set<Integer> numberOfInstallments = Set.of(6, 9, 12, 24);
    private final LoanRepository loanRepository;
    private final CustomerService customerService;
    private final LoanInstallmentService loanInstallmentService;

    public LoanService(LoanRepository loanRepository, CustomerService customerService, LoanInstallmentService loanInstallmentService) {
        this.loanRepository = loanRepository;
        this.customerService = customerService;
        this.loanInstallmentService = loanInstallmentService;
    }

    @Transactional
    public void createLoan(LoanDTO loanDto) {
        if (!customerService.customerHasLimit(loanDto.getCustomerId(), loanDto.getAmount())) {
            LOGGER.warning("The customer's limit is insufficient");
            return;
        }
        if (!numberOfInstallments.contains(loanDto.getNumberOfInstallments())) {
            LOGGER.warning("The number of installments is invalid!");
            return;
        }
        if (!(loanDto.getInterestRate() >= 0.1f && loanDto.getInterestRate() <= 0.5f)) {
            LOGGER.warning("The interest rate is invalid!");
            return;
        }
        double totalAmount = loanDto.getAmount() * (1 + loanDto.getInterestRate());

        Loan loan = new Loan(loanDto.getCustomerId(), totalAmount, loanDto.getNumberOfInstallments(), LocalDate.now(), false);
        Loan savedLoan = loanRepository.save(loan);
        loanInstallmentService.generateInstallments(savedLoan.getId(), savedLoan.getLoanAmount(), savedLoan.getNumberOfInstallment());
    }

    public List<Loan> listLoans(long customerId) {
        return loanRepository.findByCustomerId(customerId);
    }

}
