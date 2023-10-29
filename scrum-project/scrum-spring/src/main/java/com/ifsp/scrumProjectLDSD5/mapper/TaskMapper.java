package com.ifsp.scrumProjectLDSD5.mapper;

import org.springframework.stereotype.Component;

import com.ifsp.scrumProjectLDSD5.dto.TaskDTO;
import com.ifsp.scrumProjectLDSD5.entity.Task;

@Component
public class TaskMapper {

    public TaskDTO toDTO(Task task) {
        if (task == null) {
            return null;
        }

        return new TaskDTO(task.getId(),
                task.getTitle(),
                task.getSprint(),
                task.getAssignee(),
                task.getReporter(),                
                task.getAssignmentDate(),
                task.getEndDate(),
                task.getStoryPoints(),
                task.getDescription(),
                task.getUserStory(),
                task.getStatus());
    }

    public Task toEntity(TaskDTO taskDTO) {
        if (taskDTO == null) {
            return null;
        }

        Task task = new Task();

        if (taskDTO.id() != null) {
            task.setId(taskDTO.id());
        }

        task.setTitle(taskDTO.title());
        task.setSprint(taskDTO.sprint());
        task.setAssignee(taskDTO.assignee());
        task.setReporter(taskDTO.reporter());        
        task.setAssignmentDate(taskDTO.assignmentDate());
        task.setEndDate(taskDTO.endDate());
        task.setStoryPoints(taskDTO.storyPoints());
        task.setDescription(taskDTO.description());
        task.setUserStory(taskDTO.userStory());
        task.setStatus(taskDTO.status());

        return task;
    }
}
