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
               project.getAssignee(),
               project.getAssignmentDate(),
               project.getEndDate(),
               project.getUserList(),
               project.getSprints(),
               project.getUserStories()

       );
    }
    public Project toEntity(ProjectDTO projectDTO) {
        if (projectDTO == null) {
            return null;
        }

        Project project = new Project();
        project.setId(Long.valueOf(projectDTO.getId()));
        project.setTitle(projectDTO.getTitle());
        project.setAssignee(projectDTO.getAssignee());
        project.setAssignmentDate(projectDTO.getAssignmentDate());
        project.setEndDate(projectDTO.getEndDate());
        project.setUserList(projectDTO.getUserList());
        project.setSprints(projectDTO.getSprints());
        project.setUserStories(projectDTO.getUserStories());

        return project;
    }



}
