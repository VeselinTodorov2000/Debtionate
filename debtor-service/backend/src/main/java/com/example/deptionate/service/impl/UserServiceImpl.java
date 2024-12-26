package com.example.deptionate.service.impl;

import com.example.deptionate.entity.User;
import com.example.deptionate.repository.UserDao;
import com.example.deptionate.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserDao userDao;

    @Override
    public User create(User user) {
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        User createdUser = userDao.save(user);
        logger.info("User with id: {} created: ", createdUser.getId());
        return createdUser;
    }

    @Override
    public Optional<User> findById(Long id) {
        logger.debug("Find user by id: {}", id);
        return userDao.findById(id);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        logger.debug("Find user by email: {}", email);
        return userDao.findByEmail(email);
    }

    @Override
    public User update(User user) {
        User existingUser = userDao.findById(user.getId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());
        if (user.getPassword() != null && !user.getPassword().isEmpty()) {
            existingUser.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        logger.info("User with id:{} is being updated", existingUser.getId());
        return userDao.save(existingUser);
    }

    @Override
    public boolean delete(Long id) {
        Optional<User> user = userDao.findById(id);

        if (user.isPresent()) {
            userDao.delete(user.get());
            logger.info("User with id:{} is deleted", id);
            return true;
        }
        logger.info("User with id:{} failed to delete", id);
        return false;
    }
}
