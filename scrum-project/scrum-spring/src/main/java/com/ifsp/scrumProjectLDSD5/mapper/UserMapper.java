package com.ifsp.scrumProjectLDSD5.mapper;

import org.springframework.stereotype.Component;

import com.ifsp.scrumProjectLDSD5.dto.UserDTO;
import com.ifsp.scrumProjectLDSD5.entity.User;

@Component
public class UserMapper {
    
    public UserDTO toDTO(User user) {
        if(user == null) {
            return null;
        }

        return new UserDTO(
            user.getId(),
            user.getUsername(),
            user.getPassword(),
            user.getEmail(),
            user.getMemberProjects(),
            user.getReporterProjects(),
            user.getReporterSprints(),
            user.getAssigneeUserStories(),
            user.getReporterUserStories(),
            user.getAssigneeTasks(),
            user.getReporterTasks()
        );
    }
    
    public User toEntity(UserDTO userDTO) {
        if(userDTO == null) {
            return null;
        }

        User user = new User();

        if(userDTO.id() != null) {
            user.setId(userDTO.id());
        }

        user.setUsername(userDTO.username());
        user.setPassword(userDTO.password());
        user.setEmail(userDTO.email());
        user.setMemberProjects(userDTO.memberProjects());
        user.setReporterProjects(userDTO.reporterProjects());
        user.setReporterSprints(userDTO.reporterSprints());
        user.setAssigneeUserStories(userDTO.assigneeUserStories());
        user.setReporterUserStories(userDTO.reporterUserStories());
        user.setAssigneeTasks(userDTO.assigneeTasks());
        user.setReporterTasks(userDTO.reporterTasks());

        return user;
    }
}
