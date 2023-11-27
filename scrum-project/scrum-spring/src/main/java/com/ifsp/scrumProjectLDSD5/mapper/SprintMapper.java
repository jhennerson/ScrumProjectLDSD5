package com.ifsp.scrumProjectLDSD5.mapper;

import org.springframework.stereotype.Component;

import com.ifsp.scrumProjectLDSD5.dto.SprintDTO;
import com.ifsp.scrumProjectLDSD5.entity.Sprint;

@Component
public class SprintMapper {
    
    public SprintDTO toDTO(Sprint sprint) {
        if(sprint == null) {
            return null;
        }

        return new SprintDTO(
            sprint.getId(),
            sprint.getTitle(),
            sprint.getProject(),
            sprint.getReporter(),
            sprint.getDescription(),
            sprint.getAssignmentDate(),
            sprint.getEndDate(),
            sprint.getTasks()
        );
    }

    public Sprint toEntity(SprintDTO sprintDTO) {
        if(sprintDTO == null) {
            return null;
        }

        Sprint sprint = new Sprint();

        if(sprintDTO.id() != null) {
            sprint.setId(sprintDTO.id());
        }

        sprint.setTitle(sprintDTO.title());
        sprint.setReporter(sprintDTO.reporter());
        sprint.setProject(sprintDTO.project());
        sprint.setDescription(sprintDTO.description());
        sprint.setAssignmentDate(sprintDTO.assignmentDate());
        sprint.setEndDate(sprintDTO.endDate());
        sprint.setTasks(sprintDTO.tasks());

        return sprint;
    }
}
