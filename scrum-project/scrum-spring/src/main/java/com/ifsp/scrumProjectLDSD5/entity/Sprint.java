package com.ifsp.scrumProjectLDSD5.entity;

import java.util.Date;

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

@Table(name = "sprints")
@Entity(name = "sprints")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Sprint {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
	private String id;
    
    @NotBlank
    @NotNull
    private String title;

    @ManyToOne
    private User reporter;

    private String description;

    private Date assignmentDate;

	private Date endDate;
}
