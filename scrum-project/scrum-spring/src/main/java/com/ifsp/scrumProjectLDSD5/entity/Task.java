package com.ifsp.scrumProjectLDSD5.entity;

import java.util.Date;

import com.ifsp.scrumProjectLDSD5.enumeration.Status;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="tasks")
public class Task  {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@NotNull
	private String title;

	@ManyToOne
	private Person person;

	private Date assignmentDate;

	private Date endDate;

	private Integer storyPoints;

	private String description;

	@ManyToOne
	private UserStory userStory;

	@Enumerated(EnumType.STRING)
	private Status status;	
	
	public Task() {}
	
	public Task(Task task) {
		this.title = task.getTitle();
		this.person = task.getPerson();
		this.assignmentDate = task.getAssignmentDate();
		this.endDate = task.getEndDate();
		this.storyPoints = task.getStoryPoints();
		this.description = task.getDescription();
		this.userStory = task.getUserStory();
		this.status = task.getStatus();
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

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Date getAssignmentDate() {
		return assignmentDate;
	}

	public void setAssignmentDate(Date assignmentDate) {
		this.assignmentDate = assignmentDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Integer getStoryPoints() {
		return storyPoints;
	}

	public void setStoryPoints(Integer storyPoints) {
		this.storyPoints = storyPoints;
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
