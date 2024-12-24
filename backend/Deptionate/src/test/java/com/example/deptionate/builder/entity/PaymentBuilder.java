package com.example.deptionate.builder.entity;

import com.example.deptionate.entity.Debt;
import com.example.deptionate.entity.Payment;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class PaymentBuilder {
    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Debt debt;
    private BigDecimal amount;
    private LocalDate paymentDate;

    private PaymentBuilder() {}

    public static PaymentBuilder aPayment() {
        return new PaymentBuilder();
    }

    public PaymentBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public PaymentBuilder withCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public PaymentBuilder withUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public PaymentBuilder withDebt(Debt debt) {
        this.debt = debt;
        return this;
    }

    public PaymentBuilder withAmount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public PaymentBuilder withPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
        return this;
    }

    public Payment build() {
        Payment payment = new Payment();
        payment.setId(id);
        payment.setCreatedAt(createdAt);
        payment.setUpdatedAt(updatedAt);
        payment.setDebt(debt);
        payment.setAmount(amount);
        payment.setPaymentDate(paymentDate);
        return payment;
    }
}
