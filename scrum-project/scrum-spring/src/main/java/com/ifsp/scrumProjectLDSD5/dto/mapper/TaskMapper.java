package com.ifsp.scrumProjectLDSD5.dto.mapper;

import org.springframework.stereotype.Component;

import com.ifsp.scrumProjectLDSD5.dto.TaskDTO;
import com.ifsp.scrumProjectLDSD5.model.Task;

@Component
public class TaskMapper {

    public TaskDTO toDTO(Task task) {
        if (task == null) {
            return null;
        }

        return new TaskDTO(task.getId(),
                task.getTitle(),
                task.getUser(),
                task.getAssignmentDate(),
                task.getEndDate(),
                task.getEffort(),
                task.getDescription(),
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
        task.setUser(taskDTO.user());
        task.setAssignmentDate(taskDTO.assignmentDate());
        task.setEndDate(taskDTO.endDate());
        task.setDescription(taskDTO.description());
        task.setStatus(taskDTO.status());

        return task;
    }
}
