package com.ifsp.scrumProjectLDSD5.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ifsp.scrumProjectLDSD5.entity.Sprint;
import com.ifsp.scrumProjectLDSD5.entity.User;
import com.ifsp.scrumProjectLDSD5.entity.UserStory;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;


public record ProjectDTO (
    @JsonProperty("id")
    Long id,

    @NotBlank
    @NotBlank
    String title,

    User reporter,

    Date assignmentDate,

    Date endDate,

    List<User> members,

    List<UserStory> userStories,

    List<Sprint> sprints
){}