package com.ifsp.scrumProjectLDSD5.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ifsp.scrumProjectLDSD5.entity.User;

import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserStoryDTO(
	@JsonProperty("id")
	String id,

	@NotBlank
	@NotNull
	String title,

	@ManyToOne
	User assignee,

	@ManyToOne
	User reporter,

	String description
) {}
