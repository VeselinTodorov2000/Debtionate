package com.example.deptionate.service.impl;

import com.example.deptionate.entity.Debt;
import com.example.deptionate.repository.DebtDao;
import com.example.deptionate.service.DebtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DebtServiceImpl implements DebtService {
    private final Logger logger = LoggerFactory.getLogger(DebtServiceImpl.class);
    @Autowired
    private DebtDao debtDao;

    @Override
    public Debt create(Debt debt) {
        Debt createdDebt = debtDao.save(debt);
        logger.info("Debt with id: {} created: ", createdDebt.getId());
        return createdDebt;
    }

    @Override
    public Optional<Debt> findById(Long id) {
        logger.debug("Find debt by id: {}", id);
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
        logger.info("Debt with id:{} is being updated", existingDebt.getId());
        return debtDao.save(existingDebt);
    }

    @Override
    public boolean delete(Long id) {
        Optional<Debt> debt = debtDao.findById(id);

        if (debt.isPresent()) {
            debtDao.delete(debt.get());
            logger.info("Payment with id:{} is deleted", id);
            return true;
        }
        logger.info("Payment with id:{} failed to delete", id);
        return false;
    }

    @Override
    public List<Debt> findDebtsByUserId(Long userId) {
        logger.debug("Find debts for a user with id: {}", userId);
        return debtDao.findDebtByUserId(userId);
    }

}
