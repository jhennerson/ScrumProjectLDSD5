package com.ifsp.scrumProjectLDSD5.controller;

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

import com.ifsp.scrumProjectLDSD5.dto.AuthRequestDTO;
import com.ifsp.scrumProjectLDSD5.dto.AuthResponseDTO;
import com.ifsp.scrumProjectLDSD5.dto.RegisterDTO;
import com.ifsp.scrumProjectLDSD5.entity.User;
import com.ifsp.scrumProjectLDSD5.repository.UserRepository;
import com.ifsp.scrumProjectLDSD5.service.TokenService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;

    private final TokenService tokenService;

    public AuthController(AuthenticationManager authenticationManager, UserRepository userRepository, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthRequestDTO authRequestDTO){
        try {
            UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(
                    authRequestDTO.username(), authRequestDTO.password());
            Authentication auth = this.authenticationManager.authenticate(usernamePassword);

            String token = tokenService.generateToken((User) auth.getPrincipal());

            return ResponseEntity.ok(new AuthResponseDTO(token));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        } catch (LockedException e) {
            return ResponseEntity.status(HttpStatus.LOCKED).body("Account is locked");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
        }
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO user){
        if(this.repository.findByUsername(user.username()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(user.password());
        User newUser = new User();

        newUser.setUsername(user.username());
        newUser.setEmail(user.email());
        newUser.setPassword(encryptedPassword);

        if(user.role() != null) {
            newUser.setRole(user.role());
        }

        this.userRepository.save(newUser);

        return ResponseEntity.ok().build();
    }
}
