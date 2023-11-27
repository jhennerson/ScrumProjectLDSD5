package com.ifsp.scrumProjectLDSD5.entity;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.*;
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

@Entity
@Table(name = "sprints")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@JsonIgnoreProperties({"deleted", "tasks"})
@SQLDelete(sql = "UPDATE sprints SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class Sprint implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
	private String id;
    
    @NotBlank
    @NotNull
    private String title;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToOne
    @JoinColumn(name = "reporter_id")
    private User reporter;

    private String description;

    private Date assignmentDate;

	private Date endDate;

    @OneToMany(mappedBy = "sprint")
    private List<Task> tasks = new ArrayList<>();

    private Boolean deleted = false;
}
