package com.ifsp.scrumProjectLDSD5.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ifsp.scrumProjectLDSD5.model.Backlog;
import com.ifsp.scrumProjectLDSD5.service.BacklogService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/backlog")
public class BacklogController {
    @Autowired
    private BacklogService backlogService;

    @PostMapping
    public Backlog createBacklog(@RequestBody Backlog backlog) {
        return backlogService.createBacklog(backlog);
    }

    @GetMapping("/{id}")
    public Optional<Backlog> getBacklogById(@PathVariable Long id) {
        return backlogService.getBacklogById(id);
    }

    @PutMapping("/{id}")
    public Backlog updateBacklog(@PathVariable Long id, @RequestBody Backlog updatedBacklog) {
        return backlogService.updateBacklog(id, updatedBacklog);
    }

    @DeleteMapping("/{id}")
    public void deleteBacklog(@PathVariable Long id) {
        backlogService.deleteBacklog(id);
    }

    @GetMapping("/all")
    public List<Backlog> getAllBacklogs() {
        return backlogService.getAllBacklogs();
    }
}