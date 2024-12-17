package com.example.deptionate.service.impl;

import com.example.deptionate.entity.Debt;
import com.example.deptionate.entity.User;
import com.example.deptionate.repository.DebtDao;
import com.example.deptionate.repository.UserDao;
import com.example.deptionate.service.DebtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DebtServiceImpl implements DebtService {
    @Autowired
    private DebtDao debtDao;
    @Autowired
    private UserDao userDao;

    @Override
    public Debt create(Debt debt) {
        return debtDao.save(debt);
    }

    @Override
    public Optional<Debt> findById(Long id) {
        return debtDao.findById(id);
    }

    @Override
    public Debt update(Debt debt) {
        Debt existingDebt = debtDao.findById(debt.getId())
                .orElseThrow(() -> new RuntimeException("Debt not found"));

        existingDebt.setUser(debt.getUser());
        existingDebt.setAmount(debt.getAmount());
        existingDebt.setStatus(debt.getStatus());
        existingDebt.setDueDate(debt.getDueDate());
        return debtDao.save(existingDebt);
    }

    @Override
    public boolean delete(Long id) {
        Optional<Debt> debt = debtDao.findById(id);

        if (debt.isPresent()) {
            debtDao.delete(debt.get());
            return true;
        }
        return false;
    }

    @Override
    public List<Debt> findDebtsByUserId(Long userId) {
        return debtDao.findDebtByUserId(userId);
    }

}
