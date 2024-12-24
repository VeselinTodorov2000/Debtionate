package com.example.deptionate.mapper;

import com.example.deptionate.builder.dto.PaymentDtoBuilder;
import com.example.deptionate.builder.entity.DebtBuilder;
import com.example.deptionate.builder.entity.PaymentBuilder;
import com.example.deptionate.entity.Debt;
import com.example.deptionate.entity.Payment;
import com.example.deptionate.model.PaymentDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static java.math.BigDecimal.valueOf;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
class PaymentMapperTest {
    @InjectMocks
    private PaymentMapper paymentMapper;

    @Test
    public void mapDtoToEntity_shouldNotMapToEntity_whenDto_isNull() {
        assertNull(paymentMapper.mapDtoToEntity(null, new Debt()));
    }

    @Test
    void mapDtoToEntity_shouldMapToEntity_whenDto_isNotNull() {
        PaymentDto paymentDto = PaymentDtoBuilder.aPaymentDto()
                .withDebtId(1L)
                .withPaymentDate(LocalDate.of(2020, 5, 2))
                .withAmount(valueOf(10))
                .build();
        Debt debt = DebtBuilder.aDebt()
                .withStatus("DUE")
                .build();

        Payment payment = paymentMapper.mapDtoToEntity(paymentDto, debt);

        assertThat(payment.getDebt()).isEqualTo(debt);
        assertThat(payment.getAmount()).isEqualTo(paymentDto.getAmount());
        assertThat(payment.getPaymentDate()).isEqualTo(paymentDto.getPaymentDate());
    }

    @Test
    public void mapEntityToDto_shouldNotMapToDto_whenEntity_isNull() {
        assertNull(paymentMapper.mapEntityToDto(null));
    }

    @Test
    void mapEntityToDto_shouldMapToDto_whenEntity_isNotNull() {
        Payment payment = PaymentBuilder.aPayment()
                .withDebt(DebtBuilder.aDebt().withId(3L).build())
                .withPaymentDate(LocalDate.of(2020, 5, 2))
                .withAmount(valueOf(25))
                .build();

        PaymentDto paymentDto = paymentMapper.mapEntityToDto(payment);

        assertThat(paymentDto.getDebtId()).isEqualTo(payment.getDebt().getId());
        assertThat(paymentDto.getPaymentDate()).isEqualTo(payment.getPaymentDate());
        assertThat(paymentDto.getAmount()).isEqualTo(payment.getAmount());
    }
}