package com.ing.hubs.loan_api.service;

import com.ing.hubs.loan_api.entity.Loan;
import com.ing.hubs.loan_api.entity.LoanInstallment;
import com.ing.hubs.loan_api.repository.LoanInstallmentRepository;
import com.ing.hubs.loan_api.repository.LoanRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Service
public class LoanInstallmentService {
    private static final Logger LOGGER = Logger.getLogger(LoanInstallmentService.class.getName());
    private final LoanInstallmentRepository loanInstallmentRepository;
    private final LoanRepository loanRepository;
    private final CustomerService customerService;

    public LoanInstallmentService(LoanInstallmentRepository loanInstallmentRepository, LoanRepository loanRepository, CustomerService customerService) {
        this.loanInstallmentRepository = loanInstallmentRepository;
        this.loanRepository = loanRepository;
        this.customerService = customerService;
    }

    void generateInstallments(long loanId, double loanAmount, int numberOfInstallments) {
        for (int i = 0; i < numberOfInstallments; i++) {
            LocalDate dueDate = LocalDate.now().plusMonths(i).with(TemporalAdjusters.firstDayOfNextMonth());
            loanInstallmentRepository.save(new LoanInstallment(loanId, loanAmount / numberOfInstallments, 0.0d, dueDate, null, false));
        }
    }

    public List<LoanInstallment> listInstallmentByLoanId(long loanId) {
        return loanInstallmentRepository.findByLoanIdOrderByDueDateAsc(loanId);
    }

    LoanInstallment payLoanInstallment(LoanInstallment loanInstallment) {
        //discount and penalty logic
        LocalDate today = LocalDate.now();
        LocalDate dueDate = loanInstallment.getDueDate();
        long dayDiff = ChronoUnit.DAYS.between(dueDate, today);
        double newAmount = loanInstallment.getAmount() * (1 + (dayDiff * 0.001));

        loanInstallment.setPaidAmount(newAmount);
        loanInstallment.setPaymentDate(LocalDate.now());
        loanInstallment.setPaid(true);
        return loanInstallmentRepository.save(loanInstallment);
    }

    @Transactional
    public Map<String, String> payLoan(Long loanId, Double amount) {
        List<LoanInstallment> loanInstallmentList = listInstallmentByLoanId(loanId);
        List<LoanInstallment> paidLoanInstallmentList = new ArrayList<>();
        for (LoanInstallment loanInstallment : loanInstallmentList) {
            if (loanInstallment.isPaid()) {
                continue;
            }
            LocalDate firstDayOfCurrentMonth = LocalDate.now().withDayOfMonth(1);
            Period diff = Period.between(firstDayOfCurrentMonth, loanInstallment.getDueDate());
            int totalMonthDiff = (diff.getYears() * 12) + diff.getMonths();
            if (totalMonthDiff >= 3) {
                LOGGER.warning("The due date of installment is more than 3 calendar months!");
                break;
            }
            if (amount - loanInstallment.getAmount() < 0) {
                LOGGER.warning("The paid amount is insufficient for installmentId:" + loanInstallment.getId());
                break;
            }
            paidLoanInstallmentList.add(payLoanInstallment(loanInstallment));
            amount = amount - loanInstallment.getPaidAmount();
        }

        int paidInstallmentCount = paidLoanInstallmentList.size();
        if (paidInstallmentCount == 0) {
            LOGGER.warning("Payment failed!");
            return Map.of("error", "Payment failed!");
        }

        //Updating the customer used credit limit
        double totalAmountSpent = paidInstallmentCount * paidLoanInstallmentList.getFirst().getAmount();
        Loan loan = loanRepository.findById(loanId).get();
        customerService.updateUsedCreditLimit(loan.getCustomerId(), totalAmountSpent);

        //Marking the loan as paid
        boolean isAllPaid = loanInstallmentList.stream().allMatch(LoanInstallment::isPaid);
        if (isAllPaid) {
            loan.setPaid(true);
            loanRepository.save(loan);
            LOGGER.info("setPaid changed as true for loanId:" + loan.getId());
        }

        return Map.of("paidInstallmentCount", String.valueOf(paidInstallmentCount),
                "totalAmountSpent",String.valueOf(totalAmountSpent),
                "isPaidCompletely",String.valueOf(isAllPaid));
    }
}
