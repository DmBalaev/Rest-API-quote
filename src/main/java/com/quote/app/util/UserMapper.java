package com.quote.app.util;

import com.quote.app.payload.responses.UserDto;
import com.quote.app.persistance.entity.User;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class UserMapper implements Function<User, UserDto> {
    @Override
    public UserDto apply(User user) {
        return new UserDto(
                user.getName(),
                user.getEmail()
        );
    }
}
