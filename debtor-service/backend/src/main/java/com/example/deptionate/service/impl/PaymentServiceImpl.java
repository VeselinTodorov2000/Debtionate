package com.example.deptionate.service.impl;

import com.example.deptionate.entity.Payment;
import com.example.deptionate.repository.PaymentDao;
import com.example.deptionate.service.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService {
    private final Logger logger = LoggerFactory.getLogger(PaymentServiceImpl.class);
    @Autowired
    private PaymentDao paymentDao;

    @Override
    public Payment create(Payment payment) {
        Payment createdPayment = paymentDao.save(payment);
        logger.info("Payment with id: {} created: ", createdPayment.getId());
        return createdPayment;
    }

    @Override
    public Optional<Payment> findById(Long id) {
        logger.debug("Find payment by id: {}", id);
        return paymentDao.findById(id);
    }

    @Override
    public List<Payment> findPaymentsByDebtId(Long debtId) {
        logger.debug("Find payments by debt id: {}", debtId);
        return paymentDao.findPaymentsByDebtId(debtId);
    }

    @Override
    public Payment update(Payment payment) {
        Payment existingPayment = paymentDao.findById(payment.getId())
                .orElseThrow(() -> new RuntimeException("Payment not found"));

        existingPayment.setPaymentDate(payment.getPaymentDate());
        existingPayment.setDebt(payment.getDebt());
        existingPayment.setAmount(payment.getAmount());

        logger.info("Payment with id:{} is being updated", existingPayment.getId());
        return paymentDao.save(existingPayment);
    }

    @Override
    public boolean delete(Long id) {
        Optional<Payment> payment = paymentDao.findById(id);

        if (payment.isPresent()) {
            paymentDao.delete(payment.get());
            logger.info("Payment with id:{} is deleted", id);
            return true;
        }
        logger.info("Payment with id:{} failed to delete", id);
        return false;
    }
}
