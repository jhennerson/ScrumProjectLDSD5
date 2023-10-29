package com.ifsp.scrumProjectLDSD5.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ifsp.scrumProjectLDSD5.entity.Person;
import com.ifsp.scrumProjectLDSD5.entity.Task;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SprintDTO(
    @JsonProperty("id")
    Long id,
    
    @NotBlank
    @NotNull
    String title,
    
    Person assignee,

    String description,

    Date assignmentDate,

	Date endDate,

    @Valid
    List<Task> tasks
) {}
