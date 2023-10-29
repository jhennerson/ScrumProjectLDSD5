package com.ifsp.scrumProjectLDSD5.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PersonDTO(
	@JsonProperty("id")
	Long id,

	@NotBlank
	@NotNull
	String username,

	@NotBlank
	@NotNull
	String email
) {}
