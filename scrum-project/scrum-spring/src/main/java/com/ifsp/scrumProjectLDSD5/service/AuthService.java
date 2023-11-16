package com.ifsp.scrumProjectLDSD5.service;

import com.ifsp.scrumProjectLDSD5.dto.AuthRequestDTO;
import com.ifsp.scrumProjectLDSD5.dto.AuthResponseDTO;
import com.ifsp.scrumProjectLDSD5.dto.RegisterDTO;
import com.ifsp.scrumProjectLDSD5.entity.User;
import com.ifsp.scrumProjectLDSD5.exception.UserAlreadyExistsException;
import com.ifsp.scrumProjectLDSD5.mapper.RegisterMapper;
import com.ifsp.scrumProjectLDSD5.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
public class AuthService /*implements UserDetailsService*/ {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final PasswordEncoder passwordEncoder;
    private final RegisterMapper registerMapper;

    public AuthResponseDTO login(@RequestBody @Valid AuthRequestDTO user){
        UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(
                user.username(), user.password());
        Authentication auth = this.authenticationManager.authenticate(usernamePassword);

        String token = tokenService.generateToken((User) auth.getPrincipal());

        return new AuthResponseDTO(token);
    }

    public RegisterDTO register(@RequestBody @Valid RegisterDTO user){
        if(userRepository.findByUsername(user.username()) != null){
            throw new UserAlreadyExistsException(user.username());
        }

        User newUser = registerMapper.toEntity(user);

        newUser.setPassword(passwordEncoder.encode(user.password()));

        return registerMapper.toDTO(userRepository.save(newUser));
    }
}
