package com.ifsp.scrumProjectLDSD5.dto;


import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ifsp.scrumProjectLDSD5.entity.Person;
import com.ifsp.scrumProjectLDSD5.entity.Sprint;
import com.ifsp.scrumProjectLDSD5.entity.UserStory;
import com.ifsp.scrumProjectLDSD5.enumeration.Status;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record TaskDTO(
	@JsonProperty("id")
	Long id,

	@NotBlank
	@NotNull
	String title,

	Sprint sprint,	

	Person assignee,

	Person reporter,

	Date assignmentDate,

	Date endDate,

	Integer storyPoints,

	String description,

	UserStory userStory,

	@Enumerated(EnumType.STRING)
	Status status
	) {}