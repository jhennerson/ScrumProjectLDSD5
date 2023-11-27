package com.ifsp.scrumProjectLDSD5.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ifsp.scrumProjectLDSD5.enumeration.UserRole;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegisterDTO(

    @NotBlank
	@NotNull
    String username,

    @NotBlank
	@NotNull
    String password,

    @NotBlank
	@NotNull
    String email
) {}
