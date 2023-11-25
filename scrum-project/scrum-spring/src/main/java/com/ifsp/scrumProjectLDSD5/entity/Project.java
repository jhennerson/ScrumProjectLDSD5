package com.ifsp.scrumProjectLDSD5.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "projects")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@SQLDelete(sql = "UPDATE projects SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @NotNull
    @NotBlank
    private String title;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "reporter_id")
    private User reporter;

    private Date assignmentDate;

    private Date endDate;

    private Boolean deleted = false;

    @JsonBackReference
    @ManyToMany(mappedBy = "memberProjects")
    private List<User> members;

    @JsonBackReference
    @OneToMany(mappedBy = "project")
    private List<UserStory> userStories = new ArrayList<>();

    @JsonBackReference
    @OneToMany(mappedBy = "project")
    private List<Sprint> sprints = new ArrayList<>();
}
