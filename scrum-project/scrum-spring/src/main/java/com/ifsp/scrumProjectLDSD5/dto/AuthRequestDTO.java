package com.ifsp.scrumProjectLDSD5.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
public record AuthRequestDTO(
    @NotBlank
	@NotNull
    String username,

    @NotBlank
	@NotNull
    String password
) {}
