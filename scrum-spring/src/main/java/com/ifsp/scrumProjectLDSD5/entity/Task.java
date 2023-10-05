package com.ifsp.scrumProjectLDSD5.entity;

import java.time.LocalDateTime;

import com.ifsp.scrumProjectLDSD5.enumeration.Status;
import com.ifsp.scrumProjectLDSD5.form.TaskForm;
import com.ifsp.scrumProjectLDSD5.interfaces.ITask;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="TASK")
public class Task  {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String title;
	@ManyToOne
	private User user;
	private LocalDateTime assignmentDate;
	private LocalDateTime endDate;
	private Integer effort;
	private String description;
	@ManyToOne
	private UserStory userStory;
	@Enumerated(EnumType.STRING)
	private Status status;	
	
	public Task() {}
	
	public Task(TaskForm taskForm) {
		this.title = taskForm.getTitle();
		this.user = new User();
		this.assignmentDate = taskForm.getAssignmentDate();
		this.endDate = taskForm.getEndDate();
		this.effort = taskForm.getEffort();
		this.description = taskForm.getDescription();
		this.status = taskForm.getStatus();
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

	public UserStory getUserStory() {
		return userStory;
	}

	public void setUserStory(UserStory userStory) {
		this.userStory = userStory;
	}
}
