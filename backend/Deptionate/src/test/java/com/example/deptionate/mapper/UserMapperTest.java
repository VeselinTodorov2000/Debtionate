package com.example.deptionate.mapper;

import com.example.deptionate.builder.dto.UserDtoBuilder;
import com.example.deptionate.builder.entity.UserBuilder;
import com.example.deptionate.entity.User;
import com.example.deptionate.model.UserDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
class UserMapperTest {
    @InjectMocks
    private UserMapper userMapper;

    @Test
    public void mapDtoToEntity_shouldNotMapToEntity_whenDto_isNull() {
        assertNull(userMapper.mapDtoToEntity(null));
    }

    @Test
    void mapDtoToEntity_shouldMapToEntity_whenDto_isNotNull() {
        UserDto userDto = UserDtoBuilder.aUserDto()
                .withName("John")
                .withEmail("email@gmail.com")
                .withPassword("pass")
                .build();

        User user = userMapper.mapDtoToEntity(userDto);

        assertThat(user.getName()).isEqualTo(userDto.getName());
        assertThat(user.getEmail()).isEqualTo(userDto.getEmail());
        assertThat(user.getPassword()).isEqualTo(userDto.getPassword());
    }

    @Test
    public void mapEntityToDto_shouldNotMapToDto_whenEntity_isNull() {
        assertNull(userMapper.mapEntityToDto(null));
    }

    @Test
    void mapEntityToDto_shouldMapToDto_whenEntity_isNotNull() {
        User user = UserBuilder.aUser()
                .withName("John")
                .withEmail("email@gmail.com")
                .withPassword("pass")
                .build();

        UserDto userDto = userMapper.mapEntityToDto(user);

        assertThat(userDto.getName()).isEqualTo(user.getName());
        assertThat(userDto.getEmail()).isEqualTo(user.getEmail());
        assertThat(userDto.getPassword()).isEqualTo(user.getPassword());
    }
}