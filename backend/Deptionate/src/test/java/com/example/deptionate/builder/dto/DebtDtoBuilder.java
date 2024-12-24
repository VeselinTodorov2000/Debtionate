package com.example.deptionate.builder.dto;

import com.example.deptionate.model.DebtDto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class DebtDtoBuilder {
    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long userId;
    private BigDecimal amount;
    private LocalDate dueDate;
    private String status;

    private DebtDtoBuilder() {}

    public static DebtDtoBuilder aDebtDto() {
        return new DebtDtoBuilder();
    }

    public DebtDtoBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public DebtDtoBuilder withCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public DebtDtoBuilder withUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public DebtDtoBuilder withUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public DebtDtoBuilder withAmount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public DebtDtoBuilder withDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
        return this;
    }

    public DebtDtoBuilder withStatus(String status) {
        this.status = status;
        return this;
    }

    public DebtDto build() {
        DebtDto debtDto = new DebtDto();
        debtDto.setId(id);
        debtDto.setCreatedAt(createdAt);
        debtDto.setUpdatedAt(updatedAt);
        debtDto.setUserId(userId);
        debtDto.setAmount(amount);
        debtDto.setDueDate(dueDate);
        debtDto.setStatus(status);
        return debtDto;
    }
}
