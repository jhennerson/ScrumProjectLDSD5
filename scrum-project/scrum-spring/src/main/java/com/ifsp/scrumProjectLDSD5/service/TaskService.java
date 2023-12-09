package com.ifsp.scrumProjectLDSD5.service;

import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;

import com.ifsp.scrumProjectLDSD5.dto.TaskDTO;
import com.ifsp.scrumProjectLDSD5.exception.RecordNotFoundException;
import com.ifsp.scrumProjectLDSD5.mapper.TaskMapper;
import com.ifsp.scrumProjectLDSD5.repository.TaskRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@Validated
@Service
@RequiredArgsConstructor
public class TaskService {

	private final TaskRepository taskRepository;
	private final TaskMapper taskMapper;

	public List<TaskDTO> list() {
		return taskRepository.findAll()
							 .stream()
							 .map(taskMapper::toDTO)
							 .collect(Collectors.toList());
	}

	public TaskDTO findById(@PathVariable @NotNull String id) {
		return taskRepository.findById(id)
							 .map(taskMapper::toDTO)
							 .orElseThrow(() -> new RecordNotFoundException(id));
	}

	public TaskDTO create(@Valid @NotNull TaskDTO task) {
		return taskMapper.toDTO(taskRepository.save(taskMapper.toEntity(task)));
	}

	public TaskDTO update(@NotNull String id, @Valid @NotNull TaskDTO task) {
		return taskRepository.findById(id)
				.map(recordFound -> {
					recordFound.setTitle(task.title());
					recordFound.setSprint(task.sprint());
					recordFound.setUserStory(task.userStory());
					recordFound.setAssignee(task.assignee());
					recordFound.setReporter(task.reporter());
					recordFound.setAssignmentDate(task.assignmentDate());
					recordFound.setEndDate(task.endDate());
					recordFound.setStatus(task.status());
					recordFound.setStoryPoints(task.storyPoints());
					recordFound.setDescription(task.description());

					return taskMapper.toDTO(taskRepository.save(recordFound));
				}).orElseThrow(() -> new RecordNotFoundException(id));
	}

	public void delete(@NotNull String id) {
		taskRepository.delete(taskRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id)));
	}

	public List<TaskDTO> listBySprintId(String sprintId) {
		return taskRepository.findBySprintId(sprintId)
				.stream()
				.map(taskMapper::toDTO)
				.collect(Collectors.toList());
	}
}
