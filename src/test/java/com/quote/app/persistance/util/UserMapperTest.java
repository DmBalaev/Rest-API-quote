package com.quote.app.persistance.util;

import com.quote.app.payload.responses.UserDto;
import com.quote.app.persistance.entity.User;
import com.quote.app.util.UserMapper;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UserMapperTest {
    private final UserMapper userMapper = new UserMapper();

    @Test
    void apply_shouldMapUserToUserDto() {
        User user = User.builder().name("John Doe").email("john@example.com").build();

        UserDto userDto = userMapper.apply(user);

        assertThat(userDto.name()).isEqualTo("John Doe");
        assertThat(userDto.email()).isEqualTo("john@example.com");
    }

}