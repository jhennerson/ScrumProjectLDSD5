package com.ifsp.scrumProjectLDSD5.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.ifsp.scrumProjectLDSD5.entity.User;
import com.ifsp.scrumProjectLDSD5.enumeration.Status;
import com.ifsp.scrumProjectLDSD5.interfaces.ITask;


@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class TaskDTO implements ITask{
	
	private Long id;
	private String title;
	private UserDTO user;
	private LocalDateTime assignmentDate;
	private LocalDateTime EndDate;
	private Integer effort;
	private String description;
	private Status status;
	
	
	
	public void removePassword() {
		try {
			this.getUser().removePassword();
		}catch (Exception e) {
		}
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
	public UserDTO getUser() {
		return user;
	}
	public void setUser(UserDTO user) {
		this.user = user;
	}
	public LocalDateTime getAssignmentDate() {
		return assignmentDate;
	}
	public void setAssignmentDate(LocalDateTime assignmentDate) {
		this.assignmentDate = assignmentDate;
	}
	public LocalDateTime getEndDate() {
		return EndDate;
	}
	public void setEndDate(LocalDateTime endDate) {
		EndDate = endDate;
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
}
