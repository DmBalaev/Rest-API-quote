package com.quote.app.service;

import com.quote.app.exception.DuplicateException;
import com.quote.app.payload.requests.UserRequest;
import com.quote.app.payload.responses.UserDto;
import com.quote.app.persistance.entity.User;
import com.quote.app.persistance.repository.UserRepository;
import com.quote.app.util.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserDto createUser(UserRequest response){
        if (userRepository.existsByEmail(response.getEmail())){
            throw new DuplicateException("A user with this email already exists");
        }
        var user = User.builder()
                .name(response.getName())
                .email(response.getEmail())
                .password(response.getPassword())
                .created(LocalDateTime.now())
                .build();

        userRepository.save(user);

        return userMapper.apply(user);
    }
}
