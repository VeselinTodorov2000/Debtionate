package com.deptionate.creditorservice.dao;

import com.deptionate.creditorservice.entity.DebtPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DebtPaymentRepository extends JpaRepository<DebtPayment, Long> {
}
