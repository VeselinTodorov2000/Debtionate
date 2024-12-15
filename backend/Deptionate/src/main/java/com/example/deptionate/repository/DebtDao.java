package com.example.deptionate.repository;

import com.example.deptionate.entity.Debt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DebtDao extends JpaRepository<Debt, Long> {
}
