package com.example.deptionate.repository;

import com.example.deptionate.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Long> {
}
