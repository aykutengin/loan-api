package com.ing.hubs.loan_api.dto;

import jakarta.validation.constraints.NotNull;

public class LoanDTO {
    @NotNull(message = "customerId is mandatory!")
    private long customerId;
    @NotNull(message = "loan amount is mandatory!")
    private double amount;
    private float interestRate;
    @NotNull(message = "number of installments is mandatory!")
    private int numberOfInstallments;

    public LoanDTO(long customerId, double amount, float interestRate, int numberOfInstallments) {
        this.customerId = customerId;
        this.amount = amount;
        this.interestRate = interestRate;
        this.numberOfInstallments = numberOfInstallments;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public float getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(float interestRate) {
        this.interestRate = interestRate;
    }

    public int getNumberOfInstallments() {
        return numberOfInstallments;
    }

    public void setNumberOfInstallments(int numberOfInstallments) {
        this.numberOfInstallments = numberOfInstallments;
    }
}
