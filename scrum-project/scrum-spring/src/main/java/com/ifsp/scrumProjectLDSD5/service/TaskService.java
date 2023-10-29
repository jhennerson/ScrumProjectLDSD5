package com.ifsp.scrumProjectLDSD5.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;

import com.ifsp.scrumProjectLDSD5.dto.TaskDTO;
import com.ifsp.scrumProjectLDSD5.mapper.TaskMapper;
import com.ifsp.scrumProjectLDSD5.exception.RecordNotFoundException;
import com.ifsp.scrumProjectLDSD5.repository.TaskRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Validated
@Service
public class TaskService {

	private final TaskRepository taskRepository;
	private final TaskMapper taskMapper;

	public TaskService(TaskRepository taskRepository, TaskMapper taskMapper) {
		this.taskRepository = taskRepository;
		this.taskMapper = taskMapper;
	}

	public List<TaskDTO> list() {
		return taskRepository.findAll()
							 .stream()
							 .map(taskMapper::toDTO)
							 .collect(Collectors.toList());
	}

	public TaskDTO findById(@PathVariable @NotNull @Positive Long id) {
		return taskRepository.findById(id)
							 .map(taskMapper::toDTO)
							 .orElseThrow(() -> new RecordNotFoundException(id));
	}

	public TaskDTO create(@Valid @NotNull TaskDTO task) {
		return taskMapper.toDTO(taskRepository.save(taskMapper.toEntity(task)));
	}

	public TaskDTO update(@NotNull @Positive Long id, @Valid @NotNull TaskDTO task) {
		return taskRepository.findById(id)
				.map(recordFound -> {
					recordFound.setTitle(task.title());
					recordFound.setSprint(task.sprint());
					recordFound.setPerson(task.person());
					recordFound.setAssignmentDate(task.assignmentDate());
					recordFound.setEndDate(task.endDate());
					recordFound.setStatus(task.status());
					recordFound.setStoryPoints(task.storyPoints());
					recordFound.setDescription(task.description());

					return taskMapper.toDTO(taskRepository.save(recordFound));
				}).orElseThrow(() -> new RecordNotFoundException(id));
	}

	public void delete(@NotNull @Positive Long id) {
		taskRepository.delete(taskRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id)));
	}
}
