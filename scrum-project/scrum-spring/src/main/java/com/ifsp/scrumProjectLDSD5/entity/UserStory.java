package com.ifsp.scrumProjectLDSD5.entity;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import jakarta.persistence.Entity;
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

@Table(name = "user-stories")
@Entity(name = "user-stories")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@SQLDelete(sql = "UPDATE user-stories SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class UserStory {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;

	@NotBlank
	@NotNull
	private String title;

	@ManyToOne
	private User assignee;

	@ManyToOne
	private User reporter;

	private String description;

	private Boolean deleted = false;
}
