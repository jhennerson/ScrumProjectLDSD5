package com.ifsp.scrumProjectLDSD5.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ifsp.scrumProjectLDSD5.entity.Person;

import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserStoryDTO(
	@JsonProperty("id")
	Long id,

	@NotBlank
	@NotNull
	String title,

	@ManyToOne
	Person assignee,

	@ManyToOne
	Person reporter,

	String description
) {}
