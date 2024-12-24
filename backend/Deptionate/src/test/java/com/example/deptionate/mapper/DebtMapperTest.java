package com.example.deptionate.mapper;

import com.example.deptionate.builder.dto.DebtDtoBuilder;
import com.example.deptionate.builder.entity.DebtBuilder;
import com.example.deptionate.builder.entity.UserBuilder;
import com.example.deptionate.entity.Debt;
import com.example.deptionate.entity.User;
import com.example.deptionate.model.DebtDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static java.math.BigDecimal.valueOf;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
class DebtMapperTest {
    @InjectMocks
    private DebtMapper debtMapper;

    @Test
    public void mapDtoToEntity_shouldNotMapToEntity_whenDto_isNull() {
        assertNull(debtMapper.mapDtoToEntity(null, new User()));
    }

    @Test
    void mapDtoToEntity_shouldMapToEntity_whenDto_isNotNull() {
        DebtDto debtDto = DebtDtoBuilder.aDebtDto()
                .withUserId(1L)
                .withAmount(valueOf(10))
                .withDueDate(LocalDate.of(2020, 5, 12))
                .withStatus("DUE")
                .build();
        User user = UserBuilder.aUser()
                .withName("John Smith")
                .withEmail("john123@gmail.com")
                .withPassword("123")
                .build();

        Debt debt = debtMapper.mapDtoToEntity(debtDto, user);

        assertThat(debt.getUser()).isEqualTo(user);
        assertThat(debt.getAmount()).isEqualTo(debtDto.getAmount());
        assertThat(debt.getDueDate()).isEqualTo(debtDto.getDueDate());
        assertThat(debt.getStatus()).isEqualTo(debtDto.getStatus());
    }

    @Test
    public void mapEntityToDto_shouldNotMapToDto_whenEntity_isNull() {
        assertNull(debtMapper.mapEntityToDto(null));
    }

    @Test
    void mapEntityToDto_shouldMapToDto_whenEntity_isNotNull() {
        User user = UserBuilder.aUser()
                .withName("John Smith")
                .withEmail("john123@gmail.com")
                .withPassword("123")
                .build();
        Debt debt = DebtBuilder.aDebt()
                .withUser(user)
                .withAmount(valueOf(10))
                .withDueDate(LocalDate.of(2020, 5, 12))
                .withStatus("DUE")
                .build();

        DebtDto debtDto = debtMapper.mapEntityToDto(debt);

        assertThat(debtDto.getUserId()).isEqualTo(user.getId());
        assertThat(debtDto.getAmount()).isEqualTo(debt.getAmount());
        assertThat(debtDto.getDueDate()).isEqualTo(debt.getDueDate());
        assertThat(debtDto.getStatus()).isEqualTo(debt.getStatus());
    }
}