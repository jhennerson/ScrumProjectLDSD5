package com.ifsp.scrumProjectLDSD5.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthDTO {
    @NotNull(message = "Username Não pode ser nulo")
    @NotBlank(message = "Username Não pode ser vazio")
    private String username;
    @NotNull(message = "Password Não pode ser nulo")
    @NotBlank(message = "Password Não pode ser vazio")
    private String password;
    private String email;
}
