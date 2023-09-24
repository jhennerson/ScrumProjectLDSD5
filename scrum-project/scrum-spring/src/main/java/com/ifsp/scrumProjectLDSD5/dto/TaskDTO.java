package com.ifsp.scrumProjectLDSD5.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ifsp.scrumProjectLDSD5.enumeration.Status;
import com.ifsp.scrumProjectLDSD5.model.User;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TaskDTO(
	@JsonProperty("id")
	Long id,
	@NotBlank
	@NotNull
	String title,
	@ManyToOne
	User user,
	LocalDateTime assignmentDate,
	LocalDateTime endDate,
	Integer effort,
	String description,
	@Enumerated(EnumType.STRING)
	Status status) {
}
