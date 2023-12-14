package com.quote.app.controller;

import com.quote.app.payload.requests.UserRequest;
import com.quote.app.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;

    @PostMapping()
    public ResponseEntity<?> create(@Valid @RequestBody UserRequest request){
        return new ResponseEntity<>(userService.createUser(request), HttpStatus.CREATED);
    }
}
