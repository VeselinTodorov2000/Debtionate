package com.example.deptionate.service;

import com.example.deptionate.entity.Payment;

import java.util.List;
import java.util.Optional;

public interface PaymentService {
    Payment create(Payment payment);

    Optional<Payment> findById(Long id);

    Payment update(Payment payment);

    boolean delete(Long id);

    List<Payment> findPaymentsByDebtId(Long debtId);
}
