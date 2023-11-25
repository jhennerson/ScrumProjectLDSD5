package com.ifsp.scrumProjectLDSD5.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ifsp.scrumProjectLDSD5.entity.Project;
import com.ifsp.scrumProjectLDSD5.entity.Task;
import com.ifsp.scrumProjectLDSD5.entity.User;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SprintDTO(
    @JsonProperty("id")
    Long id,
    
    @NotBlank
    @NotNull
    String title,

    Project project,
    
    User reporter,

    String description,

    Date assignmentDate,

	Date endDate,

    List<Task> tasks
) {}
