package com.example.deptionate.repository;

import com.example.deptionate.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentDao extends JpaRepository<Payment, Long> {
}
