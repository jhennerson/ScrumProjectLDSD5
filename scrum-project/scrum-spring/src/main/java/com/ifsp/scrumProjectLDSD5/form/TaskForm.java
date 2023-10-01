package com.ifsp.scrumProjectLDSD5.form;

import java.time.LocalDateTime;

import com.ifsp.scrumProjectLDSD5.entity.Task;
import com.ifsp.scrumProjectLDSD5.enumeration.Status;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class TaskForm {
	
	private Long id;
	@NotNull(message = "invalid title: cannot be null")
	@NotBlank(message = "Invalid title: cannot be blank")
	private String title;
	private Long userId;
	private LocalDateTime assignmentDate;
	private LocalDateTime endDate;
	private Integer effort;
	private String description;
	private Status status;
	
	public Task toEntity(TaskForm taskform) {
		return new Task(taskform);
	}	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public LocalDateTime getAssignmentDate() {
		return assignmentDate;
	}
	public void setAssignmentDate(LocalDateTime assignmentDate) {
		this.assignmentDate = assignmentDate;
	}
	public LocalDateTime getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}
	public Integer getEffort() {
		return effort;
	}
	public void setEffort(Integer effort) {
		this.effort = effort;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}	
}
