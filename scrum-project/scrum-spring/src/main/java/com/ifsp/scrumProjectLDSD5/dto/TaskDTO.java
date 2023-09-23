package com.ifsp.scrumProjectLDSD5.dto;

import java.time.LocalDateTime;

import com.ifsp.scrumProjectLDSD5.enumeration.Status;
import com.ifsp.scrumProjectLDSD5.model.User;



public class TaskDTO {
	
	private Long id;
	private String tittle;
	private User user;
	private LocalDateTime assignmentDate;
	private LocalDateTime EndDate;
	private Integer effort;
	private String description;
	private Status status;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTittle() {
		return tittle;
	}
	public void setTittle(String tittle) {
		this.tittle = tittle;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
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
