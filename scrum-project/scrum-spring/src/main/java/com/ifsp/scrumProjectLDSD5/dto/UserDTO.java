package com.ifsp.scrumProjectLDSD5.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.ifsp.scrumProjectLDSD5.entity.Project;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@JsonIgnoreProperties({"password", "role", "deleted", "enabled", "authorities", "accountNonExpired", "credentialsNonExpired", "accountNonLocked"})
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
	String email,

	List<Project> projects
) {}
