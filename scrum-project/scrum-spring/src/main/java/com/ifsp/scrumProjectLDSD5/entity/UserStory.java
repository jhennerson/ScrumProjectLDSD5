package com.ifsp.scrumProjectLDSD5.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user_stories")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@SQLDelete(sql = "UPDATE user_stories SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class UserStory {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;

	@NotBlank
	@NotNull
	private String title;

	@JsonManagedReference
	@ManyToOne
	@JoinColumn(name = "project_id")
	private Project project;

	@JsonManagedReference
	@ManyToOne
	@JoinColumn(name = "assignee_id")
	private User assignee;

	@JsonManagedReference
	@ManyToOne
	@JoinColumn(name = "reporter_id")
	private User reporter;

	private String description;

	@JsonBackReference
	@OneToMany(mappedBy = "userStory")
	private List<Task> tasks = new ArrayList<>();

	private Boolean deleted = false;
}
