package com.ifsp.scrumProjectLDSD5.entity;

import com.fasterxml.jackson.annotation.*;
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

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user_stories")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@JsonIgnoreProperties("deleted")
@SQLDelete(sql = "UPDATE user_stories SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class UserStory implements Serializable {
	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@NotNull
	private String title;

	@ManyToOne
	@JoinColumn(name = "project_id")
	private Project project;

	@ManyToOne
	@JoinColumn(name = "assignee_id")
	private User assignee;

	@ManyToOne
	@JoinColumn(name = "reporter_id")
	private User reporter;

	private String description;

	@JsonIgnore
	@OneToMany(mappedBy = "userStory")
	private List<Task> tasks = new ArrayList<>();

	private Boolean deleted = false;
}
