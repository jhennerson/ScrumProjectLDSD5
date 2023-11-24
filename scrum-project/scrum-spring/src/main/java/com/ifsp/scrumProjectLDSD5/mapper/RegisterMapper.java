package com.ifsp.scrumProjectLDSD5.mapper;

import com.ifsp.scrumProjectLDSD5.dto.RegisterDTO;
import com.ifsp.scrumProjectLDSD5.entity.User;
import org.springframework.stereotype.Component;

@Component
public class RegisterMapper {
    public RegisterDTO toDTO(User user) {
        if(user == null) {
            return null;
        }

        return new RegisterDTO(
                user.getUsername(),
                user.getPassword(),
                user.getEmail()
        );
    }

    public User toEntity(RegisterDTO registerDTO) {
        if(registerDTO == null) {
            return null;
        }

        User user = new User();

        user.setUsername(registerDTO.username());
        user.setPassword(registerDTO.password());
        user.setEmail(registerDTO.email());

        return user;
    }
}
