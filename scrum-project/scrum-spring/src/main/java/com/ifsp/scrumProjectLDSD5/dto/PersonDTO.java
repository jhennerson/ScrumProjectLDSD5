package com.ifsp.scrumProjectLDSD5.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ifsp.scrumProjectLDSD5.entity.Sprint;
import com.ifsp.scrumProjectLDSD5.entity.Task;
import com.ifsp.scrumProjectLDSD5.entity.UserStory;

import jakarta.validation.Valid;
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
