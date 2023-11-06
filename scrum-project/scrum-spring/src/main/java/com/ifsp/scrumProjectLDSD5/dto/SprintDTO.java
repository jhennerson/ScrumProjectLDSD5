package com.ifsp.scrumProjectLDSD5.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ifsp.scrumProjectLDSD5.entity.User;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SprintDTO(
    @JsonProperty("id")
    String id,
    
    @NotBlank
    @NotNull
    String title,
    
    User reporter,

    String description,

    Date assignmentDate,

	Date endDate
) {}
