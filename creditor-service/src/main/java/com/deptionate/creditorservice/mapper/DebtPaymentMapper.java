package com.deptionate.creditorservice.mapper;

import com.deptionate.creditorservice.entity.DebtPayment;
import com.deptionate.creditorservice.model.Payment;
import org.springframework.stereotype.Service;

@Service
public class DebtPaymentMapper {
    public DebtPayment mapEntityFromPayment(Payment payment) {
        DebtPayment debtPayment = new DebtPayment();
        debtPayment.setDebtId(payment.getDebt().getId());
        debtPayment.setPaymentId(payment.getId());
        debtPayment.setDebtAmountStart(payment.getDebt().getAmount());
        debtPayment.setDebtAmountEnd(payment.getDebt().getAmount().subtract(payment.getAmount()));

        return debtPayment;
    }
}
