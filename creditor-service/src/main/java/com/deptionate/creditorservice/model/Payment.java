package com.deptionate.creditorservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Payment extends AbstractEntity {
    @JsonProperty("debt")
    private Debt debt;

    @JsonProperty("amount")
    private BigDecimal amount;

    @JsonProperty("paymentDate")
    private LocalDate paymentDate;

    public Debt getDebt() {
        return debt;
    }

    public void setDebt(Debt debt) {
        this.debt = debt;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }
}
