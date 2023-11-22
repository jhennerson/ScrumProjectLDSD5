package com.ifsp.scrumProjectLDSD5.dto;


import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ifsp.scrumProjectLDSD5.entity.User;
import com.ifsp.scrumProjectLDSD5.entity.Sprint;
import com.ifsp.scrumProjectLDSD5.entity.UserStory;
import com.ifsp.scrumProjectLDSD5.enumeration.Status;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record TaskDTO(
	@JsonProperty("id")
	String id,

	@NotBlank
	@NotNull
	String title,

	Sprint sprint,

	UserStory userStory,

	User assignee,

	User reporter,

	Date assignmentDate,

	Date endDate,

	Integer storyPoints,

	String description,

	@Enumerated(EnumType.STRING)
	Status status
	) {}