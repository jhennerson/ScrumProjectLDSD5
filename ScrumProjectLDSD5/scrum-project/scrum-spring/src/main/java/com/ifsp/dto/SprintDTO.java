package com.ifsp.dto;
import com.ifsp.model.Backlog;
import com.ifsp.model.Sprint;
import com.ifsp.enumeration.Status;
import com.ifsp.model.User;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class SprintDTO {
    private Long id;
    private String title;
    private Status status;
    private User user;
    private Backlog backlog;
    private String description;


    public SprintDTO() {

    }

    public SprintDTO(Sprint sprint) {
        this.id = sprint.getId();
        this.title = sprint.getTitle();
        this.status = sprint.getStatus();
        this.user = sprint.getUser();
        this.backlog = sprint.getBacklog();
        this.description = sprint.getDescription();
    }
}