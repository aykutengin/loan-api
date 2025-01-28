package com.ing.hubs.loan_api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class LoanInstallment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long loanId;
    private double amount;
    private double paidAmount;
    private LocalDate dueDate;
    private LocalDate paymentDate;
    private boolean isPaid;

    public LoanInstallment() {
    }

    public LoanInstallment(long loanId, double amount, double paidAmount, LocalDate dueDate, LocalDate paymentDate, boolean isPaid) {
        this.loanId = loanId;
        this.amount = amount;
        this.paidAmount = paidAmount;
        this.dueDate = dueDate;
        this.paymentDate = paymentDate;
        this.isPaid = isPaid;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getLoanId() {
        return loanId;
    }

    public void setLoanId(long loanId) {
        this.loanId = loanId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(double paidAmount) {
        this.paidAmount = paidAmount;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }
}
