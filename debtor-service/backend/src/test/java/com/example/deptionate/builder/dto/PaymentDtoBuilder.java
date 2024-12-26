package com.example.deptionate.builder.dto;

import com.example.deptionate.model.PaymentDto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class PaymentDtoBuilder {
    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long debtId;
    private BigDecimal amount;
    private LocalDate paymentDate;

    private PaymentDtoBuilder() {}

    public static PaymentDtoBuilder aPaymentDto() {
        return new PaymentDtoBuilder();
    }

    public PaymentDtoBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public PaymentDtoBuilder withCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public PaymentDtoBuilder withUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public PaymentDtoBuilder withDebtId(Long debtId) {
        this.debtId = debtId;
        return this;
    }

    public PaymentDtoBuilder withAmount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public PaymentDtoBuilder withPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
        return this;
    }

    public PaymentDto build() {
        PaymentDto paymentDto = new PaymentDto();
        paymentDto.setId(id);
        paymentDto.setCreatedAt(createdAt);
        paymentDto.setUpdatedAt(updatedAt);
        paymentDto.setDebtId(debtId);
        paymentDto.setAmount(amount);
        paymentDto.setPaymentDate(paymentDate);
        return paymentDto;
    }
}
