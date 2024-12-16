package com.example.deptionate.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PaymentDto extends AbstractDto {
    private DebtDto debt;
    private BigDecimal amount;
    private LocalDateTime paymentDate;

    public DebtDto getDebt() {
        return debt;
    }

    public void setDebt(DebtDto debt) {
        this.debt = debt;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }
}
