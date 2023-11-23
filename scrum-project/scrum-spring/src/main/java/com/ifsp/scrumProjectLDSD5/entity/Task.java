package com.ifsp.scrumProjectLDSD5.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.ifsp.scrumProjectLDSD5.enumeration.Status;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "tasks")
@Entity(name = "tasks")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@JsonIgnoreProperties("deleted")
@SQLDelete(sql = "UPDATE tasks SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class Task  {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	
	@NotBlank
	@NotNull
	private String title;

	@ManyToOne(fetch = FetchType.LAZY)
	private Sprint sprint;

	@ManyToOne(fetch = FetchType.LAZY)
	private UserStory userStory;

	@ManyToOne(fetch = FetchType.LAZY)
	private User assignee;

	@ManyToOne(fetch = FetchType.LAZY)
	private User reporter;	

	private Date assignmentDate;

	private Date endDate;

	private Integer storyPoints;

	private String description;

	@Enumerated(EnumType.STRING)
	private Status status;

	private Boolean deleted = false;
}
