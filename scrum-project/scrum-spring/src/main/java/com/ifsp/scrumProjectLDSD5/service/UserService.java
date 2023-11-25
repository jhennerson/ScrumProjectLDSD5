package com.ifsp.scrumProjectLDSD5.service;

import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.ifsp.scrumProjectLDSD5.dto.UserDTO;
import com.ifsp.scrumProjectLDSD5.exception.RecordNotFoundException;
import com.ifsp.scrumProjectLDSD5.mapper.UserMapper;
import com.ifsp.scrumProjectLDSD5.repository.UserRepository;

import jakarta.validation.constraints.NotNull;

@Validated
@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public List<UserDTO> list() {
        return userRepository.findAll()
                               .stream()
                               .map(userMapper::toDTO)
                               .collect(Collectors.toList());
    }

    public UserDTO findById(@NotNull Long id) {
        return userRepository.findById(id)
                               .map(userMapper::toDTO)
                               .orElseThrow(() -> new RecordNotFoundException(id));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws RecordNotFoundException {
        return userRepository.findByUsername(username);
    }
}
