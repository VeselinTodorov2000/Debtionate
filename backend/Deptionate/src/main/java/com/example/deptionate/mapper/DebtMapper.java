package com.example.deptionate.mapper;

import com.example.deptionate.entity.Debt;
import com.example.deptionate.model.DebtDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DebtMapper {
    @Autowired
    private UserMapper userMapper;

    public Debt mapDtoToEntity(DebtDto dto) {
        if (dto == null) return null;

        Debt entity = new Debt();
        entity.setId(dto.getId());
        entity.setCreatedAt(dto.getCreatedAt());
        entity.setUpdatedAt(dto.getUpdatedAt());
        entity.setUser(userMapper.mapDtoToEntity(dto.getUser()));
        entity.setAmount(dto.getAmount());
        entity.setDueDate(dto.getDueDate());
        entity.setStatus(dto.getStatus());

        return entity;
    }

    public DebtDto mapEntityToDto(Debt entity) {
        if (entity == null) return null;

        DebtDto dto = new DebtDto();
        dto.setId(entity.getId());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());
        dto.setUser(userMapper.mapEntityToDto(entity.getUser()));
        dto.setAmount(entity.getAmount());
        dto.setDueDate(entity.getDueDate());
        dto.setStatus(entity.getStatus());

        return dto;
    }
}
