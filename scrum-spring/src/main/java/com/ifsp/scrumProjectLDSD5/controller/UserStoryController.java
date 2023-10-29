package com.ifsp.scrumProjectLDSD5.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ifsp.scrumProjectLDSD5.dto.UserStoryDTO;
import com.ifsp.scrumProjectLDSD5.service.UserStoryService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Validated
@RestController
@RequestMapping("/api/user-stories")
public class UserStoryController {
    private final UserStoryService userStoryService;

    public UserStoryController(UserStoryService userStoryService) {
        this.userStoryService = userStoryService;
    }

    @GetMapping
    public List<UserStoryDTO> list() {
        return userStoryService.list();
    }

    @GetMapping("/{id}")
    public UserStoryDTO findById(@PathVariable @NotNull @Positive Long id) {
        return userStoryService.findById(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public UserStoryDTO create(@RequestBody @Valid @NotNull UserStoryDTO userStory) {
        return userStoryService.create(userStory);
    }

    @PatchMapping("/{id}")
    public UserStoryDTO update(@PathVariable @NotNull @Positive Long id, @RequestBody @Valid @NotNull UserStoryDTO userStory) {
        return userStoryService.update(id, userStory);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @NotNull @Positive Long id) {
        userStoryService.delete(id);
    }
}
