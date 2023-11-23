package com.ifsp.scrumProjectLDSD5.controller;

import com.ifsp.scrumProjectLDSD5.exception.UserAlreadyExistsException;
import com.ifsp.scrumProjectLDSD5.mapper.RegisterMapper;
import com.ifsp.scrumProjectLDSD5.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ifsp.scrumProjectLDSD5.dto.AuthRequestDTO;
import com.ifsp.scrumProjectLDSD5.dto.AuthResponseDTO;
import com.ifsp.scrumProjectLDSD5.dto.RegisterDTO;
import com.ifsp.scrumProjectLDSD5.entity.User;
import com.ifsp.scrumProjectLDSD5.repository.UserRepository;
import com.ifsp.scrumProjectLDSD5.service.TokenService;

import jakarta.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public AuthResponseDTO login(@RequestBody @Valid AuthRequestDTO user) {
        return authService.login(user);
    }

    @PostMapping("/register")
    public RegisterDTO register(@RequestBody @Valid RegisterDTO user) {
        return authService.register(user);
    }
}
