package com.ing.hubs.loan_api.service;

import com.ing.hubs.loan_api.dto.LoanDTO;
import com.ing.hubs.loan_api.entity.Customer;
import com.ing.hubs.loan_api.entity.Loan;
import com.ing.hubs.loan_api.repository.LoanRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LoanServiceTest {

    @Mock
    LoanRepository loanRepository;
    @Mock
    CustomerService customerService;
    @Mock
    LoanInstallmentService loanInstallmentService;

    @InjectMocks
    private LoanService loanService;

    @Test
    void createLoan_validParameters_successful() {
        when(customerService.customerHasLimit(anyLong(), anyDouble())).thenReturn(true);

        Loan loan = new Loan(1L, 5000.0, 12, LocalDate.now(), false);
        when(loanRepository.save(any())).thenReturn(loan);

        doNothing().when(loanInstallmentService).generateInstallments(anyLong(), anyDouble(), anyInt());

        LoanDTO loanDTO = new LoanDTO(1L, 5000.0, 0.1f, 12);
        loanService.createLoan(loanDTO);

        verify(loanRepository).save(any());
        verify(loanInstallmentService).generateInstallments(anyLong(), anyDouble(), anyInt());
    }

    private static Stream<Arguments> provideObjectsForCreateLoan() {
        return Stream.of(
                Arguments.of(new Customer("John", "Doe", 5000.00, 1000.00), new LoanDTO(1L, 5000.0, 0.1f, 12)),
                Arguments.of(new Customer("John", "Doe", 5000.00, 0.00), new LoanDTO(1L, 5000.0, 0.1f, 16)),
                Arguments.of(new Customer("John", "Doe", 5000.00, 0.00), new LoanDTO(1L, 5000.0, 0.6f, 12))
        );
    }

    @ParameterizedTest
    @MethodSource("provideObjectsForCreateLoan")
    void createLoan_invalidParameters_unsuccessful(Customer customer, LoanDTO loanDTO) {
        when(customerService.customerHasLimit(anyLong(), anyDouble())).thenReturn(false).thenReturn(true).thenReturn(true);
        loanService.createLoan(loanDTO);
        verify(loanRepository, never()).save(any());
        verify(loanInstallmentService, never()).generateInstallments(anyLong(), anyDouble(), anyInt());
    }

    @Test
    void listLoans_givenCustomerId_successful() {
        when(loanRepository.findByCustomerIdAndOptionalFilters(anyLong(),any(),any())).thenReturn(List.of(new Loan(1L, 5000.0, 12, LocalDate.now(), false)));
        List<Loan> loanList = loanService.listLoans(1L, null, null);
        assertFalse(loanList.isEmpty());
    }

}
