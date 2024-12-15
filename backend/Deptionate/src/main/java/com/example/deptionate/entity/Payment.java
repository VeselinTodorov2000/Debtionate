package com.example.deptionate.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name="payments")
public class Payment extends AbstractEntity {
    @ManyToOne
    @JoinColumn(name = "debt_id", nullable = false)
    private Debt debt;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "payment_date", nullable = false)
    private LocalDateTime paymentDate;

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

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }
}
