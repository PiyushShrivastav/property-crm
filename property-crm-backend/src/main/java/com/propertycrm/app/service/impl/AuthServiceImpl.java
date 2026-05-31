package com.propertycrm.app.service.impl;

import com.propertycrm.app.dto.request.*;
import com.propertycrm.app.dto.response.AuthResponse;
import com.propertycrm.app.entity.User;
import com.propertycrm.app.repository.UserRepository;
import com.propertycrm.app.security.JwtUtil;
import com.propertycrm.app.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Override
    public String register(RegisterRequest request) {

        User user = User.builder()
                .name(request.getName())
                .username(request.getUsername())
                .email(request.getEmail())
                .password(
                        passwordEncoder.encode(
                                request.getPassword()
                        )
                )
                .role(request.getRole())
                .build();

        userRepository.save(user);

        return "User Registered Successfully";
    }

    @Override
    public AuthResponse login(LoginRequest request) {

        User user =
                userRepository.findByUsername(
                        request.getUsername()
                ).orElseThrow();

        if(!passwordEncoder.matches(
                request.getPassword(),
                user.getPassword())) {

            throw new RuntimeException(
                    "Invalid Credentials"
            );
        }

        String token =
                jwtUtil.generateToken(
                        user.getUsername()
                );

        return new AuthResponse(token);
    }
}