package com.ifsp.scrumProjectLDSD5.entity;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.io.Serial;
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
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Project.class)
@SQLDelete(sql = "UPDATE projects SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class Project implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
    private List<User> members = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "project")
    private List<UserStory> userStories = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "project")
    private List<Sprint> sprints = new ArrayList<>();
}
