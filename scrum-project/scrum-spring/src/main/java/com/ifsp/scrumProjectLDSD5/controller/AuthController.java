package com.ifsp.scrumProjectLDSD5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ifsp.scrumProjectLDSD5.dto.AuthDTO;
import com.ifsp.scrumProjectLDSD5.dto.LoginResponseDTO;
import com.ifsp.scrumProjectLDSD5.dto.RegisterDTO;
import com.ifsp.scrumProjectLDSD5.entity.User;
import com.ifsp.scrumProjectLDSD5.repository.UserRepository;
import com.ifsp.scrumProjectLDSD5.service.TokenService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository repository;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthDTO authenticationDTO){
        try {
            UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(authenticationDTO.username(), authenticationDTO.password());
            Authentication auth = this.authenticationManager.authenticate(usernamePassword);

            String token = tokenService.generateToken((User) auth.getPrincipal());

            return ResponseEntity.ok(new LoginResponseDTO(token));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        } catch (LockedException e) {
            return ResponseEntity.status(HttpStatus.LOCKED).body("Account is locked");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
        }
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO registerDTO){
        if(this.repository.findByUsername(registerDTO.username()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(registerDTO.password());
        User newUser = new User(registerDTO.username(), encryptedPassword, registerDTO.email(), registerDTO.role());

        this.repository.save(newUser);

        return ResponseEntity.ok().build();
    }
}
