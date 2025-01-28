package com.ing.hubs.loan_api.repository;

import com.ing.hubs.loan_api.entity.Loan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository extends CrudRepository<Loan, Long> {
    List<Loan> findByCustomerId(long customerId);


}
