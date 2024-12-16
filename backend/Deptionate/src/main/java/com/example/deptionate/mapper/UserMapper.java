package com.example.deptionate.mapper;

import com.example.deptionate.entity.User;
import com.example.deptionate.model.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User mapDtoToEntity(UserDto dto) {
        if (dto == null) return null;

        User entity = new User();
        entity.setId(dto.getId());
        entity.setCreatedAt(dto.getCreatedAt());
        entity.setUpdatedAt(dto.getUpdatedAt());
        entity.setEmail(dto.getEmail());
        entity.setName(dto.getName());
        entity.setPassword(dto.getPassword());
        return entity;
    }

    public UserDto mapEntityToDto(User entity) {
        if (entity == null) return null;

        UserDto dto = new UserDto();
        dto.setId(entity.getId());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());
        dto.setEmail(entity.getEmail());
        dto.setName(entity.getName());
        dto.setPassword(entity.getPassword());
        return dto;
    }
}
