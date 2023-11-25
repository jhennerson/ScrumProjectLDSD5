package com.ifsp.scrumProjectLDSD5.entity;

import com.fasterxml.jackson.annotation.*;
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
@JsonIgnoreProperties("deleted")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@SQLDelete(sql = "UPDATE projects SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @NotNull
    @NotBlank
    private String title;

    @ManyToOne
    @JoinColumn(name = "reporter_id")
    private User reporter;

    private Date assignmentDate;

    private Date endDate;

    private Boolean deleted = false;

    @ManyToMany(mappedBy = "memberProjects")
    private List<User> members;

    @OneToMany(mappedBy = "project")
    private List<UserStory> userStories = new ArrayList<>();

    @OneToMany(mappedBy = "project")
    private List<Sprint> sprints = new ArrayList<>();
}
