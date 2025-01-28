package com.ing.hubs.loan_api.repository;

import com.ing.hubs.loan_api.entity.Loan;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository extends CrudRepository<Loan, Long> {
    List<Loan> findByCustomerId(long customerId);

    @Query("SELECT l FROM Loan l WHERE l.customerId = :customerId " +
            "AND (:numberOfInstallment IS NULL OR l.numberOfInstallment = :numberOfInstallment) " +
            "AND (:isPaid IS NULL OR l.isPaid = :isPaid)")
    List<Loan> findByCustomerIdAndOptionalFilters(@Param("customerId") long customerId,
                                                  @Param("numberOfInstallment") Integer numberOfInstallments,
                                                  @Param("isPaid") Boolean isPaid);

}
