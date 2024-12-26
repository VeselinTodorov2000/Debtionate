package com.example.deptionate.builder.entity;

import com.example.deptionate.entity.Debt;
import com.example.deptionate.entity.User;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class DebtBuilder {
    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private User user;
    private BigDecimal amount;
    private LocalDate dueDate;
    private String status;

    private DebtBuilder() {}

    public static DebtBuilder aDebt() {
        return new DebtBuilder();
    }

    public DebtBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public DebtBuilder withCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public DebtBuilder withUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public DebtBuilder withUser(User user) {
        this.user = user;
        return this;
    }

    public DebtBuilder withAmount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public DebtBuilder withDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
        return this;
    }

    public DebtBuilder withStatus(String status) {
        this.status = status;
        return this;
    }

    public Debt build() {
        Debt debt = new Debt();
        debt.setId(id);
        debt.setCreatedAt(createdAt);
        debt.setUpdatedAt(updatedAt);
        debt.setUser(user);
        debt.setAmount(amount);
        debt.setDueDate(dueDate);
        debt.setStatus(status);
        return debt;
    }
}
