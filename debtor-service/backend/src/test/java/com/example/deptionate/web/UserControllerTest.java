package com.example.deptionate.web;

import com.example.deptionate.builder.dto.UserDtoBuilder;
import com.example.deptionate.builder.entity.UserBuilder;
import com.example.deptionate.entity.User;
import com.example.deptionate.mapper.UserMapper;
import com.example.deptionate.model.UserDto;
import com.example.deptionate.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @Mock
    private UserMapper userMapper;

    @Test
    void create_shouldReturnCreatedUser_whenValidInput() throws Exception {
        User user = UserBuilder.aUser()
                .withId(1L)
                .withName("John Doe")
                .withEmail("john.doe@example.com")
                .build();

        UserDto userDto = UserDtoBuilder.aUserDto()
                .withId(1L)
                .withName("John Doe")
                .withEmail("john.doe@example.com")
                .build();

        when(userMapper.mapDtoToEntity(any(UserDto.class))).thenReturn(user);
        when(userService.create(any(User.class))).thenReturn(user);
        when(userMapper.mapEntityToDto(user)).thenReturn(userDto);

        mockMvc.perform(post("/api/users/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"John Doe\",\"email\":\"john.doe@example.com\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.email").value("john.doe@example.com"));
    }

    @Test
    void getById_shouldReturnUser_whenUserExists() throws Exception {
        User user = UserBuilder.aUser()
                .withId(1L)
                .withName("John Doe")
                .withEmail("john.doe@example.com")
                .build();

        UserDto userDto = UserDtoBuilder.aUserDto()
                .withId(1L)
                .withName("John Doe")
                .withEmail("john.doe@example.com")
                .build();

        when(userService.findById(1L)).thenReturn(Optional.of(user));
        when(userMapper.mapEntityToDto(user)).thenReturn(userDto);

        mockMvc.perform(get("/api/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.email").value("john.doe@example.com"));
    }

    @Test
    void getById_shouldReturnNotFound_whenUserDoesNotExist() throws Exception {
        when(userService.findById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/users/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void update_shouldReturnUpdatedUser_whenValidInput() throws Exception {
        User user = UserBuilder.aUser()
                .withId(1L)
                .withName("Jane Doe")
                .withEmail("jane.doe@example.com")
                .build();

        UserDto userDto = UserDtoBuilder.aUserDto()
                .withId(1L)
                .withName("John Doe")
                .withEmail("john.doe@example.com")
                .build();

        when(userMapper.mapDtoToEntity(any(UserDto.class))).thenReturn(user);
        when(userService.update(any(User.class))).thenReturn(user);
        when(userMapper.mapEntityToDto(user)).thenReturn(userDto);

        mockMvc.perform(put("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"name\":\"Jane Doe\",\"email\":\"jane.doe@example.com\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Jane Doe"))
                .andExpect(jsonPath("$.email").value("jane.doe@example.com"));
    }

    @Test
    void delete_shouldReturnOk_whenUserExists() throws Exception {
        when(userService.delete(1L)).thenReturn(true);

        mockMvc.perform(delete("/api/users/1"))
                .andExpect(status().isOk());
    }

    @Test
    void delete_shouldReturnNotFound_whenUserDoesNotExist() throws Exception {
        when(userService.delete(1L)).thenReturn(false);

        mockMvc.perform(delete("/api/users/1"))
                .andExpect(status().isNotFound());
    }
}
