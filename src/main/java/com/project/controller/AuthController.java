package com.project.controller;

import com.project.model.dto.response.SuccessResponse;
import com.project.model.dto.request.AuthRequest;
import com.project.model.dto.response.AuthResponse;
import com.project.service.AuthService;
import com.project.service.UserService;
import com.project.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<SuccessResponse<AuthResponse>> login(@RequestBody AuthRequest authRequest ) {
        return new ResponseEntity<> (new SuccessResponse<>(authService.authenticate(authRequest)),HttpStatus.OK);
    }
}



