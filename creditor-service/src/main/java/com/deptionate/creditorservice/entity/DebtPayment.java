package com.deptionate.creditorservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "DebtPayment_X")
public class DebtPayment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(name="debt_id")
    private Long debtId;
    @Column(name="payment_id")
    private Long paymentId;
    @Column(name="debt_amount_start")
    private BigDecimal debtAmountStart;
    @Column(name="debt_amount_end")
    private BigDecimal debtAmountEnd;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDebtId() {
        return debtId;
    }

    public void setDebtId(Long debtId) {
        this.debtId = debtId;
    }

    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    public BigDecimal getDebtAmountStart() {
        return debtAmountStart;
    }

    public void setDebtAmountStart(BigDecimal debtAmountStart) {
        this.debtAmountStart = debtAmountStart;
    }

    public BigDecimal getDebtAmountEnd() {
        return debtAmountEnd;
    }

    public void setDebtAmountEnd(BigDecimal debtAmountEnd) {
        this.debtAmountEnd = debtAmountEnd;
    }
}
