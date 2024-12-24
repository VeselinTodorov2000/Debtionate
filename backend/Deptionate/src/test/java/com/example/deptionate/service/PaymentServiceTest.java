package com.example.deptionate.service;

import com.example.deptionate.builder.entity.DebtBuilder;
import com.example.deptionate.builder.entity.PaymentBuilder;
import com.example.deptionate.entity.Debt;
import com.example.deptionate.entity.Payment;
import com.example.deptionate.repository.PaymentDao;
import com.example.deptionate.service.impl.PaymentServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static java.math.BigDecimal.valueOf;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PaymentServiceTest {

    @InjectMocks
    private PaymentServiceImpl paymentService;

    @Mock
    private PaymentDao paymentDao;

    @Test
    void create_shouldCreatePayment_whenPaymentIsValid() {
        Debt debt = DebtBuilder.aDebt().withId(1L).build();
        Payment payment = PaymentBuilder.aPayment()
                .withDebt(debt)
                .withAmount(valueOf(150.0))
                .withPaymentDate(LocalDate.of(2024, 12, 24))
                .build();

        Payment createdPayment = PaymentBuilder.aPayment()
                .withDebt(debt)
                .withAmount(valueOf(150.0))
                .withPaymentDate(LocalDate.of(2024, 12, 24))
                .build();

        when(paymentDao.save(payment)).thenReturn(createdPayment);

        Payment created = paymentService.create(payment);

        verify(paymentDao).save(any());
        assertNotNull(created);
        assertEquals(payment.getAmount(), created.getAmount());
        assertEquals(payment.getPaymentDate(), created.getPaymentDate());
    }

    @Test
    void findById_shouldReturnPayment_whenPaymentIdExists() {
        Payment payment = PaymentBuilder.aPayment().withId(1L).build();

        when(paymentDao.findById(1L)).thenReturn(Optional.of(payment));

        Optional<Payment> foundPayment = paymentService.findById(1L);

        assertTrue(foundPayment.isPresent());
        assertEquals(1L, foundPayment.get().getId());
    }

    @Test
    void findById_shouldReturnEmpty_whenPaymentIdDoesNotExist() {
        when(paymentDao.findById(1L)).thenReturn(Optional.empty());

        Optional<Payment> foundPayment = paymentService.findById(1L);

        assertFalse(foundPayment.isPresent());
    }

    @Test
    void update_shouldThrowException_whenPaymentDoesNotExist() {
        Payment payment = PaymentBuilder.aPayment().withId(1L).build();

        when(paymentDao.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> paymentService.update(payment));
        assertEquals("Payment not found", exception.getMessage());
    }

    @Test
    void update_shouldUpdatePayment_whenPaymentExists() {
        Debt debt = DebtBuilder.aDebt().withId(1L).build();
        Payment existingPayment = PaymentBuilder.aPayment()
                .withId(1L)
                .withDebt(debt)
                .withAmount(valueOf(100.0))
                .withPaymentDate(LocalDate.of(2024, 12, 24))
                .build();

        Payment updatedPayment = PaymentBuilder.aPayment()
                .withId(1L)
                .withDebt(debt)
                .withAmount(valueOf(150.0))
                .withPaymentDate(LocalDate.of(2025, 1, 1))
                .build();

        when(paymentDao.findById(1L)).thenReturn(Optional.of(existingPayment));
        when(paymentDao.save(existingPayment)).thenReturn(updatedPayment);

        Payment updated = paymentService.update(updatedPayment);

        assertNotNull(updated);
        assertEquals(updatedPayment.getAmount(), updated.getAmount());
        assertEquals(updatedPayment.getPaymentDate(), updated.getPaymentDate());
    }

    @Test
    void delete_shouldNotDeletePayment_whenPaymentDoesNotExist() {
        when(paymentDao.findById(1L)).thenReturn(Optional.empty());

        boolean deleted = paymentService.delete(1L);

        assertFalse(deleted);
    }

    @Test
    void delete_shouldDeletePayment_whenPaymentExists() {
        Payment payment = PaymentBuilder.aPayment().withId(1L).build();

        when(paymentDao.findById(1L)).thenReturn(Optional.of(payment));

        boolean deleted = paymentService.delete(1L);

        verify(paymentDao).delete(payment);
        assertTrue(deleted);
    }

    @Test
    void findPaymentsByDebtId_shouldReturnPayments_whenDebtExists() {
        Debt debt = DebtBuilder.aDebt().withId(1L).build();

        Payment payment1 = PaymentBuilder.aPayment()
                .withDebt(debt)
                .withAmount(valueOf(100.0))
                .build();
        Payment payment2 = PaymentBuilder.aPayment()
                .withDebt(debt)
                .withAmount(valueOf(200.0))
                .build();

        when(paymentDao.findPaymentsByDebtId(1L)).thenReturn(List.of(payment1, payment2));

        List<Payment> payments = paymentService.findPaymentsByDebtId(1L);

        assertNotNull(payments);
        assertEquals(2, payments.size());
    }
}
