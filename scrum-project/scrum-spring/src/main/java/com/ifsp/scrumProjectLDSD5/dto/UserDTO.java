package com.ifsp.scrumProjectLDSD5.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@JsonIgnoreProperties({"password"})
public record UserDTO(
	@JsonProperty("id")
	String id,

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
