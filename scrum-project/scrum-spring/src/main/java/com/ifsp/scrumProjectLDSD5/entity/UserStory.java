package com.ifsp.scrumProjectLDSD5.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "user_story")
public class UserStory {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotBlank
	@NotNull
	private String title;

	@ManyToOne
	private Person assignee;

	@ManyToOne
	private Person reporter;

	private String description;

	public UserStory() {
	}

	public UserStory(UserStory userStory) {
		this.title = userStory.getTitle();
		this.assignee = userStory.getAssignee();
		this.reporter = userStory.getReporter();
		this.description = userStory.getDescription();
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

	public Person getAssignee() {
		return assignee;
	}

	public void setAssignee(Person assignee) {
		this.assignee = assignee;
	}

	public Person getReporter() {
		return reporter;
	}

	public void setReporter(Person reporter) {
		this.reporter = reporter;
	}

	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}	
}
