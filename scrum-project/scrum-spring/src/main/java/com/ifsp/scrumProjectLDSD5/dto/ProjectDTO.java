package com.ifsp.scrumProjectLDSD5.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ifsp.scrumProjectLDSD5.entity.Sprint;
import com.ifsp.scrumProjectLDSD5.entity.User;
import com.ifsp.scrumProjectLDSD5.entity.UserStory;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDTO {
    @JsonProperty("id")
    private String id;

    @NotBlank
    @NotBlank
    private String title;

    @ManyToOne
    private User assignee;

    @NotNull
    private Date assignmentDate;

    @NotNull
    private Date endDate;

    @Size(min = 1)
    private List<User> userList;

    @Size(min = 1)
    private List<Sprint> sprints;

    @Size(min = 1)
    private List<UserStory> userStories;

    public ProjectDTO(Long id, String title, User assignee, Date assignmentDate, Date endDate, List<User> userList, List<Sprint> sprints, List<UserStory> userStories) {
    }
}
