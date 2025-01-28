package com.ing.hubs.loan_api.repository;

import com.ing.hubs.loan_api.entity.LoanInstallment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanInstallmentRepository extends CrudRepository<LoanInstallment, Long> {
    List<LoanInstallment> findByLoanIdOrderByDueDateAsc(long loanId);
}
