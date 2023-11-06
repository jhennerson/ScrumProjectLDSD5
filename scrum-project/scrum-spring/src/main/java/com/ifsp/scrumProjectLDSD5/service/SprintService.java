package com.ifsp.scrumProjectLDSD5.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;

import com.ifsp.scrumProjectLDSD5.dto.SprintDTO;
import com.ifsp.scrumProjectLDSD5.exception.RecordNotFoundException;
import com.ifsp.scrumProjectLDSD5.mapper.SprintMapper;
import com.ifsp.scrumProjectLDSD5.repository.SprintRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@Validated
@Service
public class SprintService {
    
    private final SprintRepository sprintRepository;
    private final SprintMapper sprintMapper;

    public SprintService(SprintRepository sprintRepository, SprintMapper sprintMapper) {
        this.sprintRepository = sprintRepository;
        this.sprintMapper = sprintMapper;
    }

    public List<SprintDTO> list() {
        return sprintRepository.findAll()
                               .stream()
                               .map(sprintMapper::toDTO)
                               .collect(Collectors.toList());
    }

    public SprintDTO findById(@PathVariable @NotNull String id) {
        return sprintRepository.findById(id)
                               .map(sprintMapper::toDTO)
                               .orElseThrow(() -> new RecordNotFoundException(id));
    }

    public SprintDTO create(@Valid @NotNull SprintDTO sprint) {
        return sprintMapper.toDTO(sprintRepository.save(sprintMapper.toEntity(sprint)));
    }

    public SprintDTO update(@NotNull String id, @Valid @NotNull SprintDTO sprint) {
        return sprintRepository.findById(id)
                .map(recordFound -> {
                    recordFound.setTitle(sprint.title());
                    recordFound.setReporter(sprint.reporter());
                    recordFound.setDescription(sprint.description());
                    recordFound.setAssignmentDate(sprint.assignmentDate());
                    recordFound.setEndDate(sprint.endDate());

                    return sprintMapper.toDTO(sprintRepository.save(recordFound));
                }).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public void delete(@NotNull String id) {
        sprintRepository.delete(sprintRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id)));
    }
}
