package com.ifsp.scrumProjectLDSD5.service;

import com.ifsp.scrumProjectLDSD5.dto.ProjectDTO;
import com.ifsp.scrumProjectLDSD5.exception.RecordNotFoundException;
import com.ifsp.scrumProjectLDSD5.mapper.ProjectMapper;
import com.ifsp.scrumProjectLDSD5.repository.ProjectRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Validated
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;

    public List<ProjectDTO> list() {
        return projectRepository.findAll()
                .stream()
                .map(projectMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ProjectDTO findById(@NotNull String id) {
        return projectRepository.findById(id)
                .map(projectMapper::toDTO)
                .orElseThrow(() -> new RecordNotFoundException(id));
    }

    public ProjectDTO create(@Valid @NotNull ProjectDTO project) {
        return projectMapper.toDTO(projectRepository.save(projectMapper.toEntity(project)));
    }

    public ProjectDTO update(@NotNull String id, @Valid @NotNull ProjectDTO project) {
        return projectRepository.findById(id)
                .map(recordFound -> {
                    recordFound.setTitle(project.title());
                    recordFound.setAssignee(project.assignee());
                    recordFound.setAssignmentDate(project.assignmentDate());
                    recordFound.setEndDate(project.endDate());
                    recordFound.setUsers(project.users());
                    recordFound.setSprints(project.sprints());
                    recordFound.setUserStories(project.userStories());

                    return projectMapper.toDTO(projectRepository.save(recordFound));
                }).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public void delete(@NotNull String id) {
        projectRepository.delete(projectRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(id)));
    }
}