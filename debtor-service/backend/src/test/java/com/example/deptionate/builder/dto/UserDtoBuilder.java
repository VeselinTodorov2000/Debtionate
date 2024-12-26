package com.example.deptionate.builder.dto;

import com.example.deptionate.model.UserDto;

import java.time.LocalDateTime;

public class UserDtoBuilder {
    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String name;
    private String email;
    private String password;

    private UserDtoBuilder() {}

    public static UserDtoBuilder aUserDto() {
        return new UserDtoBuilder();
    }

    public UserDtoBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public UserDtoBuilder withCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public UserDtoBuilder withUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public UserDtoBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public UserDtoBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public UserDtoBuilder withPassword(String password) {
        this.password = password;
        return this;
    }

    public UserDto build() {
        UserDto userDto = new UserDto();
        userDto.setId(id);
        userDto.setCreatedAt(createdAt);
        userDto.setUpdatedAt(updatedAt);
        userDto.setName(name);
        userDto.setEmail(email);
        userDto.setPassword(password);
        return userDto;
    }
}
