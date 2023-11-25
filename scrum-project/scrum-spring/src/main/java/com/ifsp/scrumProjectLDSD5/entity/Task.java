package com.ifsp.scrumProjectLDSD5.entity;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.*;
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

@Entity
@Table(name = "tasks")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@JsonIgnoreProperties("deleted")
@SQLDelete(sql = "UPDATE tasks SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class Task  implements Serializable {
	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@NotNull
	private String title;

	@ManyToOne
	@JoinColumn(name = "sprint_id")
	private Sprint sprint;

	@ManyToOne
	@JoinColumn(name = "user_story_id")
	private UserStory userStory;

	@ManyToOne
	@JoinColumn(name = "assignee_id")
	private User assignee;

	@ManyToOne
	@JoinColumn(name = "reporter_id")
	private User reporter;	

	private Date assignmentDate;

	private Date endDate;

	private Integer storyPoints;

	private String description;

	@Enumerated(EnumType.STRING)
	private Status status;

	private Boolean deleted = false;
}
