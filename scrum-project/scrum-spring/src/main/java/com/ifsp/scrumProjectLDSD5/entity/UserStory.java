package com.ifsp.scrumProjectLDSD5.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

@Table(name = "user-stories")
@Entity(name = "user-stories")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@JsonIgnoreProperties("deleted")
@SQLDelete(sql = "UPDATE user-stories SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class UserStory {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;

	@NotBlank
	@NotNull
	private String title;

	@ManyToOne(fetch = FetchType.LAZY)
	private Project project;

	@ManyToOne(fetch = FetchType.LAZY)
	private User assignee;

	@ManyToOne(fetch = FetchType.LAZY)
	private User reporter;

	private String description;

	@OneToMany(mappedBy = "userStory", fetch = FetchType.LAZY)
	private List<Task> tasks = new ArrayList<>();

	private Boolean deleted = false;
}
