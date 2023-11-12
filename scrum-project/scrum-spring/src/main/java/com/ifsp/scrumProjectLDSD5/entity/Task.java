package com.ifsp.scrumProjectLDSD5.entity;

import java.util.Date;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

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
@SQLDelete(sql = "UPDATE tasks SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class Task  {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	
	@NotBlank
	@NotNull
	private String title;

	@ManyToOne
	private Sprint sprint;

	@ManyToOne
	private UserStory userStory;

	@ManyToOne
	private User assignee;

	@ManyToOne
	private User reporter;	

	private Date assignmentDate;

	private Date endDate;

	private Integer storyPoints;

	private String description;

	@Enumerated(EnumType.STRING)
	private Status status;

	private Boolean deleted = false;
}
