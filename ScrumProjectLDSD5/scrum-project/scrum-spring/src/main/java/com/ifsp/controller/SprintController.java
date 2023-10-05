package com.ifsp.controller;

import com.ifsp.dto.SprintDTO;
import com.ifsp.model.Sprint;
import com.ifsp.service.SprintService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/sprints")
public class SprintController {

    @Autowired
    private SprintService sprintService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<SprintDTO>> getAllSprints() {
        List<Sprint> sprints = sprintService.getAllSprints();
        List<SprintDTO> sprintDTOs = sprints.stream()
                .map(sprint -> modelMapper.map(sprint, SprintDTO.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(sprintDTOs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SprintDTO> getSprintById(@PathVariable Long id) {
        Sprint sprint = sprintService.getSprintById(id);
        if (sprint != null) {
            SprintDTO sprintDTO = modelMapper.map(sprint, SprintDTO.class);
            return new ResponseEntity<>(sprintDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<SprintDTO> createSprint(@RequestBody SprintDTO sprintDTO) {
        Sprint sprint = modelMapper.map(sprintDTO, Sprint.class);
        Sprint createdSprint = sprintService.createSprint(sprint);
        SprintDTO createdSprintDTO = modelMapper.map(createdSprint, SprintDTO.class);

        return new ResponseEntity<>(createdSprintDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<SprintDTO> updateSprint(@PathVariable Long id, @RequestBody SprintDTO sprintDTO) {
        if (sprintService.getSprintById(id) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Sprint sprint = modelMapper.map(sprintDTO, Sprint.class);
        sprint.setId(id);

        Sprint updatedSprint = sprintService.updateSprint(id, sprint);
        SprintDTO updatedSprintDTO = modelMapper.map(updatedSprint, SprintDTO.class);

        return new ResponseEntity<>(updatedSprintDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void> deleteSprint(@PathVariable Long id) {
        boolean deleted = sprintService.deleteSprint(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
