package com.ifsp.scrumProjectLDSD5.controller;

import com.ifsp.scrumProjectLDSD5.dto.ProjectDTO;
import com.ifsp.scrumProjectLDSD5.service.ProjectService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping
    public List<ProjectDTO> list() {
        return projectService.list();
    }

    @GetMapping("/{id}")
    public ProjectDTO findById(@PathVariable @NotNull String id) {
        return projectService.findById(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public ProjectDTO create(@RequestBody @Valid @NotNull ProjectDTO project) {
        return projectService.create(project);
    }

    @PutMapping("/{id}")
    public ProjectDTO update(@PathVariable @NotNull String id, @RequestBody @Valid @NotNull ProjectDTO projectDTO) {
        return projectService.update(id, projectDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @NotNull String id) {
        projectService.delete(id);
    }
}
