package com.example.deptionate.service;

import com.example.deptionate.builder.entity.UserBuilder;
import com.example.deptionate.entity.User;
import com.example.deptionate.repository.UserDao;
import com.example.deptionate.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @InjectMocks
    private UserServiceImpl userService;
    @Mock
    private UserDao userDao;

    @Test
    void create_shouldCreateUser_whenUserIsValid() {
        User user = UserBuilder.aUser()
                .withName("John Smith")
                .withEmail("john@gmail.com")
                .withPassword("123")
                .build();
        User created = UserBuilder.aUser()
                .withName("John Smith")
                .withEmail("john@gmail.com")
                .build();
        when(userDao.save(user)).thenReturn(created);

        User createdUser = userService.create(user);

        Mockito.verify(userDao).save(any());
        assertNotNull(createdUser);
    }

    @Test
    void findById_shouldReturnUser_whenUserId_isExisting() {
        userService.findById(1L);

        verify(userDao).findById(1L);
    }

    @Test
    void update_shouldThrowException_whenUser_doesNotExist() {
        User user = UserBuilder.aUser()
                .withId(1L)
                .build();
        when(userDao.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> userService.update(user));
        assertEquals(exception.getMessage(), "User not found");
    }

    @Test
    void update_shouldUpdateUser_whenUserExists() {
        User existingUser = UserBuilder.aUser()
                .withId(1L)
                .withName("John Smith")
                .withEmail("john@gmail.com")
                .withPassword("***")
                .build();
        User updated = UserBuilder.aUser()
                .withId(1L)
                .withName("John Doe")
                .withEmail("john@gmail.com")
                .withPassword("***")
                .build();
        when(userDao.findById(existingUser.getId())).thenReturn(Optional.of(existingUser));
        when(userDao.save(existingUser)).thenReturn(updated);

        User update = userService.update(updated);

        assertNotNull(update);
        assertEquals(update.getName(), updated.getName());
        assertEquals(update.getEmail(), updated.getEmail());
    }

    @Test
    void delete_shouldNotDeleteUser_whenUserDoesNotExists() {
        when(userDao.findById(1L)).thenReturn(Optional.empty());

        boolean deleted = userService.delete(1L);

        assertFalse(deleted);
    }

    @Test
    void delete_shouldDeleteUser_whenUserDoesExists() {
        User user = UserBuilder.aUser().withId(1L).build();
        when(userDao.findById(1L)).thenReturn(Optional.of(user));

        boolean deleted = userService.delete(1L);

        verify(userDao).delete(user);
        assertTrue(deleted);
    }
}