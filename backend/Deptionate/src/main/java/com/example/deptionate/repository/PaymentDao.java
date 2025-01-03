package com.example.deptionate.repository;

import com.example.deptionate.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentDao extends JpaRepository<Payment, Long> {
    List<Payment> findPaymentsByDebtId(Long debtId);
}
