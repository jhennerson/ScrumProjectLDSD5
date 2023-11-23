package com.ifsp.scrumProjectLDSD5.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

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
@JsonIgnoreProperties("deleted")
@SQLDelete(sql = "UPDATE sprints SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class Sprint {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
	private String id;
    
    @NotBlank
    @NotNull
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    private Project project;

    @ManyToOne(fetch = FetchType.LAZY)
    private User reporter;

    private String description;

    private Date assignmentDate;

	private Date endDate;

    @OneToMany(mappedBy = "sprint", fetch = FetchType.LAZY)
    private List<Task> tasks = new ArrayList<>();

    private Boolean deleted = false;
}
