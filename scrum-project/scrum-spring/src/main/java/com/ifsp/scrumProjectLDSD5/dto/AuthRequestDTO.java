package com.ifsp.scrumProjectLDSD5.dto;

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