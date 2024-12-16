package com.example.deptionate.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class DebtDto extends AbstractDto {
    private UserDto user;
    private BigDecimal amount;
    private LocalDate dueDate;
    private String status;

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
