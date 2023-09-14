package com.ifsp.controllers;

import com.ifsp.models.Backlog;
import com.ifsp.services.BacklogService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/backlog")
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
