package com.ifsp.scrumProjectLDSD5.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ifsp.scrumProjectLDSD5.entity.Project;
import com.ifsp.scrumProjectLDSD5.entity.Task;
import com.ifsp.scrumProjectLDSD5.entity.User;

import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;


public record UserStoryDTO(
	@JsonProperty("id")
	String id,

	@NotBlank
	@NotNull
	String title,

	Project project,

	User assignee,

	User reporter,
	String description,

	List<Task> tasks
) {}
