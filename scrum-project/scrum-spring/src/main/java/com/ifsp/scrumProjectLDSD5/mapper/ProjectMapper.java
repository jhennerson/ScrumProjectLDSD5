package com.ifsp.scrumProjectLDSD5.mapper;

import com.ifsp.scrumProjectLDSD5.dto.ProjectDTO;
import com.ifsp.scrumProjectLDSD5.entity.Project;
import org.springframework.stereotype.Component;

@Component
public class ProjectMapper {

    public ProjectDTO toDTO(Project project){
        if (project == null){
            return null;
        }

        return new ProjectDTO(
                project.getId(),
                project.getTitle(),
                project.getReporter(),
                project.getAssignmentDate(),
                project.getEndDate(),
                project.getMembers(),
                project.getUserStories(),
                project.getSprints()
        );
    }
    public Project toEntity(ProjectDTO projectDTO) {
        if (projectDTO == null) {
            return null;
        }

        Project project = new Project();
        project.setId(projectDTO.id());
        project.setTitle(projectDTO.title());
        project.setReporter(projectDTO.reporter());
        project.setAssignmentDate(projectDTO.assignmentDate());
        project.setEndDate(projectDTO.endDate());
        project.setMembers(projectDTO.members());
        project.setUserStories(projectDTO.userStories());
        project.setSprints(projectDTO.sprints());

        return project;
    }



}