package com.ifsp.scrumProjectLDSD5.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ifsp.scrumProjectLDSD5.dto.SprintDTO;
import com.ifsp.scrumProjectLDSD5.dto.UserStoryDTO;
import com.ifsp.scrumProjectLDSD5.service.SprintService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@Validated
@RestController
@RequestMapping("/api/sprints")
public class SprintController {
    
    private final SprintService sprintService;

    public SprintController(SprintService sprintService) {
        this.sprintService = sprintService;
    }

    @GetMapping
    public List<SprintDTO> list() {
        return sprintService.list();
    }

    @GetMapping("/{id}")
    public SprintDTO findById(@PathVariable @NotNull String id) {
        return sprintService.findById(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public SprintDTO create(@RequestBody @Valid @NotNull SprintDTO sprint) {
        return sprintService.create(sprint);
    }

    @PutMapping("/{id}")
    public SprintDTO update(@PathVariable @NotNull String id, @RequestBody @Valid @NotNull SprintDTO sprintDTO) {
        return sprintService.update(id, sprintDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @NotNull String id) {
        sprintService.delete(id);
    }
}
