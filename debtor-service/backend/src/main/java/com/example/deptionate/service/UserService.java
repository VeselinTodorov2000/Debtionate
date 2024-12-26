package com.example.deptionate.service;

import com.example.deptionate.entity.User;

import java.util.Optional;

public interface UserService {
    User create(User user);

    Optional<User> findById(Long id);

    Optional<User> findByEmail(String email);

    User update(User user);

    boolean delete(Long id);
}