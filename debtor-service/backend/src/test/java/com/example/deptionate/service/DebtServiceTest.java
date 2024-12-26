package com.example.deptionate.service;

import com.example.deptionate.builder.entity.DebtBuilder;
import com.example.deptionate.builder.entity.UserBuilder;
import com.example.deptionate.entity.Debt;
import com.example.deptionate.entity.User;
import com.example.deptionate.repository.DebtDao;
import com.example.deptionate.service.impl.DebtServiceImpl;
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
class DebtServiceTest {

    @InjectMocks
    private DebtServiceImpl debtService;

    @Mock
    private DebtDao debtDao;

    @Test
    void create_shouldCreateDebt_whenDebtIsValid() {
        Debt debt = DebtBuilder.aDebt()
                .withAmount(valueOf(100.0))
                .withStatus("Pending")
                .withDueDate(LocalDate.of(2024, 12, 31))
                .build();

        Debt createdDebt = DebtBuilder.aDebt()
                .withAmount(valueOf(100.0))
                .withStatus("Pending")
                .withDueDate(LocalDate.of(2024, 12, 31))
                .build();

        when(debtDao.save(debt)).thenReturn(createdDebt);

        Debt created = debtService.create(debt);

        verify(debtDao).save(any());
        assertNotNull(created);
        assertEquals(debt.getAmount(), created.getAmount());
        assertEquals(debt.getStatus(), created.getStatus());
        assertEquals(debt.getDueDate(), created.getDueDate());
    }

    @Test
    void findById_shouldReturnDebt_whenDebtIdExists() {
        Debt debt = DebtBuilder.aDebt().withId(1L).build();

        when(debtDao.findById(1L)).thenReturn(Optional.of(debt));

        Optional<Debt> foundDebt = debtService.findById(1L);

        assertTrue(foundDebt.isPresent());
        assertEquals(1L, foundDebt.get().getId());
    }

    @Test
    void findById_shouldReturnEmpty_whenDebtIdDoesNotExist() {
        when(debtDao.findById(1L)).thenReturn(Optional.empty());

        Optional<Debt> foundDebt = debtService.findById(1L);

        assertFalse(foundDebt.isPresent());
    }

    @Test
    void update_shouldThrowException_whenDebtDoesNotExist() {
        Debt debt = DebtBuilder.aDebt().withId(1L).build();

        when(debtDao.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> debtService.update(debt));
        assertEquals("Debt not found", exception.getMessage());
    }

    @Test
    void update_shouldUpdateDebt_whenDebtExists() {
        Debt existingDebt = DebtBuilder.aDebt()
                .withId(1L)
                .withAmount(valueOf(100.0))
                .withStatus("Pending")
                .withDueDate(LocalDate.of(2024, 12, 31))
                .build();

        Debt updatedDebt = DebtBuilder.aDebt()
                .withId(1L)
                .withAmount(valueOf(150.0))
                .withStatus("Paid")
                .withDueDate(LocalDate.of(2025, 1, 31))
                .build();

        when(debtDao.findById(1L)).thenReturn(Optional.of(existingDebt));
        when(debtDao.save(existingDebt)).thenReturn(updatedDebt);

        Debt updated = debtService.update(updatedDebt);

        assertNotNull(updated);
        assertEquals(updatedDebt.getAmount(), updated.getAmount());
        assertEquals(updatedDebt.getStatus(), updated.getStatus());
        assertEquals(updatedDebt.getDueDate(), updated.getDueDate());
    }

    @Test
    void delete_shouldNotDeleteDebt_whenDebtDoesNotExist() {
        when(debtDao.findById(1L)).thenReturn(Optional.empty());

        boolean deleted = debtService.delete(1L);

        assertFalse(deleted);
    }

    @Test
    void delete_shouldDeleteDebt_whenDebtExists() {
        Debt debt = DebtBuilder.aDebt().withId(1L).build();

        when(debtDao.findById(1L)).thenReturn(Optional.of(debt));

        boolean deleted = debtService.delete(1L);

        verify(debtDao).delete(debt);
        assertTrue(deleted);
    }

    @Test
    void findDebtsByUserId_shouldReturnDebts_whenUserExists() {
        User user = UserBuilder.aUser().withId(1L).build();

        Debt debt1 = DebtBuilder.aDebt()
                .withUser(user)
                .withAmount(valueOf(100.0))
                .build();
        Debt debt2 = DebtBuilder.aDebt()
                .withUser(user)
                .withAmount(valueOf(200.0))
                .build();

        when(debtDao.findDebtByUserId(1L)).thenReturn(List.of(debt1, debt2));

        List<Debt> debts = debtService.findDebtsByUserId(1L);

        assertNotNull(debts);
        assertEquals(2, debts.size());
    }
}
