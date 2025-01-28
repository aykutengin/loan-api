package com.ing.hubs.loan_api.controller;

import com.ing.hubs.loan_api.dto.LoanDTO;
import com.ing.hubs.loan_api.entity.Loan;
import com.ing.hubs.loan_api.service.LoanService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class LoanController {

    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping("/createLoan")
    ResponseEntity<Object> createLoan(@RequestBody @Valid LoanDTO loanDto) {
        try {
            loanService.createLoan(loanDto);
            return ResponseEntity.ok("Loan created successfully!");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }

    }

    @GetMapping("/listLoans")
    List<Loan> listLoans(long customerId) {
        return loanService.listLoans(customerId);
    }

}
