package com.ifsp.scrumProjectLDSD5.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.Where;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
// Classe Project
@Entity
@Table(name = "projects")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Where(clause = "deleted = false")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    private String title;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User assignee;

    @NotNull
    @NotBlank
    private Date assignmentDate;

    @NotNull
    @NotBlank
    private Date endDate;


    @OneToMany(mappedBy = "project")
    private List<User> userList = new ArrayList<>();


    @OneToMany(mappedBy = "project")
    private List<Sprint> sprints = new ArrayList<>();


    @OneToMany(mappedBy = "project")
    private List<UserStory> userStories = new ArrayList<>();
}
