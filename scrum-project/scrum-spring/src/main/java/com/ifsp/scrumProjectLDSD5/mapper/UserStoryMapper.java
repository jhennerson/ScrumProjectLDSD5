package com.ifsp.scrumProjectLDSD5.mapper;

import org.springframework.stereotype.Component;

import com.ifsp.scrumProjectLDSD5.dto.UserStoryDTO;
import com.ifsp.scrumProjectLDSD5.entity.UserStory;

@Component
public class UserStoryMapper {
    public UserStoryDTO toDTO(UserStory userStory) {
        if(userStory == null) {
            return null;
        }

        return new UserStoryDTO(
            userStory.getId(),
            userStory.getTitle(),
            userStory.getProject(),
            userStory.getAssignee(),
            userStory.getReporter(),
            userStory.getDescription(),
            userStory.getTasks()
        );
    }

    public UserStory toEntity(UserStoryDTO userStoryDTO) {
        if(userStoryDTO == null) {
            return null;
        }

        UserStory userStory = new UserStory();

        if(userStoryDTO.id() != null) {
            userStory.setId(userStoryDTO.id());
        }

        userStory.setTitle(userStoryDTO.title());
        userStory.setProject(userStoryDTO.project());
        userStory.setAssignee(userStoryDTO.assignee());
        userStory.setReporter(userStoryDTO.reporter());
        userStory.setDescription(userStoryDTO.description());
        userStory.setTasks(userStoryDTO.tasks());

        return userStory;
    }
}
