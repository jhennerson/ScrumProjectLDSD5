package com.ifsp.scrumProjectLDSD5.controller;

import java.util.List;

import com.ifsp.scrumProjectLDSD5.dto.SprintDTO;
import lombok.RequiredArgsConstructor;
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

import com.ifsp.scrumProjectLDSD5.dto.UserStoryDTO;
import com.ifsp.scrumProjectLDSD5.service.UserStoryService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user-stories")
public class UserStoryController {
    private final UserStoryService userStoryService;

    @GetMapping
    public List<UserStoryDTO> list() {
        return userStoryService.list();
    }

    @GetMapping("/{id}")
    public UserStoryDTO findById(@PathVariable @NotNull String id) {
        return userStoryService.findById(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public UserStoryDTO create(@RequestBody @Valid @NotNull UserStoryDTO userStory) {
        return userStoryService.create(userStory);
    }

    @PutMapping("/{id}")
    public UserStoryDTO update(@PathVariable @NotNull String id, @RequestBody @Valid @NotNull UserStoryDTO userStory) {
        return userStoryService.update(id, userStory);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @NotNull String id) {
        userStoryService.delete(id);
    }

    @GetMapping("/project/{projectId}")
    public List<UserStoryDTO> listByProjectId(@PathVariable @NotNull String projectId) {
        return userStoryService.listByProjectId(projectId);
    }
}
