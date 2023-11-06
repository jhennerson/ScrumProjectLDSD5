package com.ifsp.scrumProjectLDSD5.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.ifsp.scrumProjectLDSD5.dto.UserDTO;
import com.ifsp.scrumProjectLDSD5.exception.RecordNotFoundException;
import com.ifsp.scrumProjectLDSD5.mapper.UserMapper;
import com.ifsp.scrumProjectLDSD5.repository.UserRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@Validated
@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public List<UserDTO> list() {
        return userRepository.findAll()
                               .stream()
                               .map(userMapper::toDTO)
                               .collect(Collectors.toList());
    }

    public UserDTO findById(@NotNull String id) {
        return userRepository.findById(id)
                               .map(userMapper::toDTO)
                               .orElseThrow(() -> new RecordNotFoundException(id));
    }
}