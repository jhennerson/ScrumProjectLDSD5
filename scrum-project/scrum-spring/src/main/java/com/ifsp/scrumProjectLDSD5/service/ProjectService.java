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
        return projectRepository.findById(Long.valueOf(id))
                .map(projectMapper::toDTO)
                .orElseThrow(() -> new RecordNotFoundException(id));
    }

    public ProjectDTO create(@Valid @NotNull ProjectDTO project) {
        return projectMapper.toDTO(projectRepository.save(projectMapper.toEntity(project)));
    }

    public ProjectDTO update(@NotNull String id, @Valid @NotNull ProjectDTO project) {
        return projectRepository.findById(Long.valueOf(id))
                .map(recordFound -> {
                    recordFound.setTitle(project.getTitle());
                    recordFound.setAssignee(project.getAssignee());
                    recordFound.setAssignmentDate(project.getAssignmentDate());
                    recordFound.setEndDate(project.getEndDate());
                    recordFound.setUserList(project.getUserList());
                    recordFound.setSprints(project.getSprints());
                    recordFound.setUserStories(project.getUserStories());

                    return projectMapper.toDTO(projectRepository.save(recordFound));
                }).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public void delete(@NotNull String id) {
        projectRepository.delete(projectRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new RecordNotFoundException(id)));
    }
}
