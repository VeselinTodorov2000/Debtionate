package com.example.deptionate.repository;

import com.example.deptionate.entity.Debt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DebtDao extends JpaRepository<Debt, Long> {
}
