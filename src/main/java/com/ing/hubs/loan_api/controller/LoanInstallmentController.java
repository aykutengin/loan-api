package com.ing.hubs.loan_api.controller;

import com.ing.hubs.loan_api.entity.LoanInstallment;
import com.ing.hubs.loan_api.service.LoanInstallmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1")
public class LoanInstallmentController {

    private final LoanInstallmentService loanInstallmentService;

    public LoanInstallmentController(LoanInstallmentService loanInstallmentService) {
        this.loanInstallmentService = loanInstallmentService;
    }

    @GetMapping("/listInstallment")
    List<LoanInstallment> listInstallment(long loanId) {
        return loanInstallmentService.listInstallmentByLoanId(loanId);
    }

    @PutMapping("/payLoan")
    Map<String, String> payLoan(long loanId, double amount) {
        return loanInstallmentService.payLoan(loanId, amount);
    }
}
