package com.ifsp.scrumProjectLDSD5.controller;

import java.util.List;

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

import com.ifsp.scrumProjectLDSD5.dto.TaskDTO;
import com.ifsp.scrumProjectLDSD5.service.TaskService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tasks")
public class TaskController {
	
	private final TaskService taskService;

	@GetMapping
	public List<TaskDTO> list() {
		return taskService.list();
	}

	@GetMapping("/{id}")
	public TaskDTO findById(@PathVariable @NotNull Long id) {
		return taskService.findById(id);
	}

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public TaskDTO create(@RequestBody @Valid @NotNull TaskDTO task) {
		return taskService.create(task);
	}

	@PutMapping("/{id}")
	public TaskDTO update(@PathVariable @NotNull Long id, @RequestBody @Valid @NotNull TaskDTO task) {
		return taskService.update(id, task);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable @NotNull Long id) {
		taskService.delete(id);
	}
} 