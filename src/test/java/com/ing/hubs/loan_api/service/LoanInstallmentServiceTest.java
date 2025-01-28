package com.ing.hubs.loan_api.service;

import com.ing.hubs.loan_api.entity.Loan;
import com.ing.hubs.loan_api.entity.LoanInstallment;
import com.ing.hubs.loan_api.repository.LoanInstallmentRepository;
import com.ing.hubs.loan_api.repository.LoanRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LoanInstallmentServiceTest {

    @Mock
    LoanInstallmentRepository loanInstallmentRepository;
    @Mock
    LoanRepository loanRepository;
    @Mock
    CustomerService customerService;
    @InjectMocks
    private LoanInstallmentService loanInstallmentService;

    @Test
    void generateInstallmentsTest() {
        LoanInstallment loanInstallment = new LoanInstallment(1L, 500.0, 0.0, LocalDate.of(2025, 1, 1), null, false);
        when(loanInstallmentRepository.save(any())).thenReturn(loanInstallment);
        loanInstallmentService.generateInstallments(1L, 3000.0, 6);
        verify(loanInstallmentRepository, times(6)).save(any());
    }

    @Test
    void listInstallmentByLoanIdTest() {
        loanInstallmentService.listInstallmentByLoanId(1L);
        verify(loanInstallmentRepository).findByLoanIdOrderByDueDateAsc(anyLong());
    }

    @Test
    void payLoanInstallmentTest() {
        LoanInstallment loanInstallment = new LoanInstallment(1L, 500.0, 0.0, LocalDate.of(2025, 1, 1), null, false);
        loanInstallmentService.payLoanInstallment(loanInstallment);

        LocalDate today = LocalDate.now();
        LocalDate dueDate = loanInstallment.getDueDate();
        long dayDiff = ChronoUnit.DAYS.between(dueDate, today);
        assertEquals(loanInstallment.getAmount() * (1 + (dayDiff * 0.001)), loanInstallment.getPaidAmount());
        assertEquals(LocalDate.now(), loanInstallment.getPaymentDate());
        assertTrue(loanInstallment.isPaid());
        verify(loanInstallmentRepository).save(any());
    }

    @Test
    void payLoanTest() {
        List<LoanInstallment> loanInstallmentList = List.of(
                new LoanInstallment(1L, 500.0, 0.0, LocalDate.of(2025, 1, 1), null, false),
                new LoanInstallment(1L, 500.0, 0.0, LocalDate.of(2025, 2, 1), null, false),
                new LoanInstallment(1L, 500.0, 0.0, LocalDate.of(2025, 3, 1), null, false),
                new LoanInstallment(1L, 500.0, 0.0, LocalDate.of(2025, 4, 1), null, false),
                new LoanInstallment(1L, 500.0, 0.0, LocalDate.of(2025, 5, 1), null, false),
                new LoanInstallment(1L, 500.0, 0.0, LocalDate.of(2025, 6, 1), null, false));
        when(loanInstallmentRepository.findByLoanIdOrderByDueDateAsc(anyLong())).thenReturn(loanInstallmentList);

        when(loanInstallmentRepository.save(any())).thenReturn(new LoanInstallment(1L, 500.0, 500.0, LocalDate.of(2025, 1, 1), LocalDate.now(), true))
                .thenReturn(new LoanInstallment(1L, 500.0, 500.0, LocalDate.of(2025, 2, 1), LocalDate.now(), true))
                .thenReturn(new LoanInstallment(1L, 500.0, 500.0, LocalDate.of(2025, 3, 1), LocalDate.now(), true));

        Loan loan = new Loan(1L, 3000.0, 6, LocalDate.of(2024, 12, 1), false);
        when(loanRepository.findById(anyLong())).thenReturn(Optional.of(loan));

        loanInstallmentService.payLoan(1L, 2000.00);

        verify(customerService).updateUsedCreditLimit(anyLong(), anyDouble());
        assertFalse(loan.isPaid());
    }

}
