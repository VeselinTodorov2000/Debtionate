package com.deptionate.creditorservice.model;

import com.deptionate.creditorservice.entity.DebtPayment;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class Response {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("createdAt")
    private LocalDateTime createdAt;
    @JsonProperty("updatedAt")
    private LocalDateTime updatedAt;
    @JsonProperty("userId")
    private Long userId;
    @JsonProperty("amount")
    private BigDecimal amount;
    @JsonProperty("dueDate")
    private LocalDate dueDate;
    @JsonProperty("status")
    private String status;

    public Response(DebtPayment debtPayment, Payment payment) {
        this.id = debtPayment.getDebtId();
        this.createdAt = payment.getDebt().getCreatedAt();
        this.updatedAt = LocalDateTime.now();
        this.userId = payment.getDebt().getUser().getId();
        this.amount = debtPayment.getDebtAmountEnd();
        this.dueDate = payment.getDebt().getDueDate();
        this.status = debtPayment.getDebtAmountEnd().compareTo(BigDecimal.ZERO) == 0 ? "Paid" : payment.getDebt().getStatus();
    }
}
