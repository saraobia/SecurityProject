package com.project.service;

import com.project.exception.AuthException;
import com.project.model.AuthRequest;
import com.project.model.User;
import com.project.model.dto.AuthenticationResponse;
import com.project.model.enums.ErrorCode;
import com.project.repository.RoleRepository;
import com.project.repository.UserRepository;
import com.project.response.ErrorResponse;
import com.project.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    UserDetailsService userDetailsService;

    public AuthenticationResponse authentication(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        User user = userRepository
                .findByEmail(request.getEmail())
                .orElseThrow(() -> new AuthException(new ErrorResponse(ErrorCode.EUN, "User not found")));
        return AuthenticationResponse.builder()
                .accessToken(jwtUtils.generateToken(user))
                .refreshToken(jwtUtils.generateRefreshToken(user))
                .build();
    }
}
