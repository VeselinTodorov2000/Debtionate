package com.example.deptionate.mapper;

import com.example.deptionate.entity.Debt;
import com.example.deptionate.entity.Payment;
import com.example.deptionate.model.PaymentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapper {
    public Payment mapDtoToEntity(PaymentDto dto, Debt debt) {
        if (dto == null) return null;

        Payment entity = new Payment();
        entity.setId(dto.getId());
        entity.setCreatedAt(dto.getCreatedAt());
        entity.setUpdatedAt(dto.getUpdatedAt());
        entity.setDebt(debt);
        entity.setAmount(dto.getAmount());
        entity.setPaymentDate(dto.getPaymentDate());
        return entity;
    }

    public PaymentDto mapEntityToDto(Payment entity) {
        if (entity == null) return null;

        PaymentDto dto = new PaymentDto();
        dto.setId(entity.getId());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());
        dto.setDebtId(entity.getDebt().getId());
        dto.setAmount(entity.getAmount());
        dto.setPaymentDate(entity.getPaymentDate());
        return dto;
    }
}