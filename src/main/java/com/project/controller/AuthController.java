package com.project.controller;

import com.project.exception.UserGenericsException;
import com.project.exception.UserMailWrongException;
import com.project.exception.UserNotFoundException;
import com.project.exception.UserPasswordWrongException;
import com.project.model.AuthRequest;
import com.project.model.User;
import com.project.service.UserService;
import com.project.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("login")
    public ResponseEntity<String> login(@RequestBody AuthRequest authRequest) throws UserNotFoundException, UserPasswordWrongException, UserGenericsException, UserMailWrongException {
        String email = authRequest.getEmail();
        String password = authRequest.getPassword();

        boolean isAuthenticated = userService.login(email, password);
        if (isAuthenticated) {
            Optional<User> optUser = userService.getUserByEmail(email);
            if (optUser.isPresent()) {
                String token = jwtUtils.generateToken(optUser.get());
                return ResponseEntity.ok(token);
            }
            throw new UserNotFoundException(email);
        }
        //CONDITION NOT AUTHENTICATED
        throw new UserPasswordWrongException(email);
    }
}



