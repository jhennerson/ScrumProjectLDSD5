package com.ifsp.scrumProjectLDSD5.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ifsp.scrumProjectLDSD5.entity.Person;
import com.ifsp.scrumProjectLDSD5.entity.Task;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SprintDTO(
    @JsonProperty("id")
    Long id,
    
    @NotBlank
    @NotNull
    String title,
    
    Person reporter,

    String description,

    Date assignmentDate,

	Date endDate
) {}
