package com.example.deptionate.service;

import com.example.deptionate.entity.Debt;

import java.util.List;
import java.util.Optional;

public interface DebtService {
    Debt create(Debt debt);
    Optional<Debt> findById(Long id);
    Debt update(Debt debt);
    boolean delete(Long id);
    List<Debt> findDebtsByUserId(Long userId);
}
