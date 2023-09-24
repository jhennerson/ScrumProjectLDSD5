package com.ifsp.scrumProjectLDSD5.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ifsp.scrumProjectLDSD5.JPA.TaskJPA;
import com.ifsp.scrumProjectLDSD5.dto.TaskDTO;
import com.ifsp.scrumProjectLDSD5.form.TaskForm;
import com.ifsp.scrumProjectLDSD5.model.Task;

@Service
public class TaskService {

	@Autowired(required = true)
	private TaskJPA taskJPA;
	
	public ResponseEntity<List<Task>> getAllTasks() {
		return taskJPA.getAllTasks();
	}

	public ResponseEntity<Task> getTaskById(Long id) {
		return taskJPA.getTaskById(id);
	}

	public ResponseEntity<?> create(TaskForm taskForm) {
		return taskJPA.create(taskForm);
	}

	public ResponseEntity<Task> updateTask(Long id,TaskForm taskForm) {
		return taskJPA.updateTask(id,taskForm);
	}

	public ResponseEntity<?> deleteTask(Long id) {
		return taskJPA.deleteTask(id);
	}

}
