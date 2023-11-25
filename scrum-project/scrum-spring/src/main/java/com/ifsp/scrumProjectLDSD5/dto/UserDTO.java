package com.ifsp.scrumProjectLDSD5.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.ifsp.scrumProjectLDSD5.entity.Project;
import com.ifsp.scrumProjectLDSD5.entity.Sprint;
import com.ifsp.scrumProjectLDSD5.entity.Task;
import com.ifsp.scrumProjectLDSD5.entity.UserStory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@JsonIgnoreProperties({"password", "role", "enabled", "authorities", "accountNonExpired", "credentialsNonExpired", "accountNonLocked"})
public record UserDTO(
	@JsonProperty("id")
	String id,

	@NotBlank
	@NotNull
	String username,

	@NotBlank
	@NotNull
	String password,

	@NotBlank
	@NotNull
	String email,

	List<Project> memberProjects,

	List<Project> reporterProjects,

	List<Sprint> reporterSprints,

	List<UserStory> assigneeUserStories,

	List<UserStory> reporterUserStories,

	List<Task> assigneeTasks,

	List<Task> reporterTasks
) {}
