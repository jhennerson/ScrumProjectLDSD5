package com.ifsp.scrumProjectLDSD5.JPA;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.ifsp.scrumProjectLDSD5.dto.TaskDTO;
import com.ifsp.scrumProjectLDSD5.form.TaskForm;
import com.ifsp.scrumProjectLDSD5.model.Task;
import com.ifsp.scrumProjectLDSD5.model.User;
import com.ifsp.scrumProjectLDSD5.repository.TaskRepository;
import com.ifsp.scrumProjectLDSD5.repository.UserRepository;


@Component
public class TaskJPA {

	@Autowired
	private TaskRepository taskRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public ResponseEntity<List<Task>> getAllTasks() {
		try {
			List<Task> findAll = taskRepository.findAll();
			if(findAll.isEmpty()) {
				return ResponseEntity.notFound().build();
			}
			return ResponseEntity.ok(findAll);
		}catch(Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	public ResponseEntity<Task> getTaskById(Long id) {
		try {
			Optional<Task> task = taskRepository.findById(id);
			if(task.isEmpty()) {
				return ResponseEntity.notFound().build();
			}
			return ResponseEntity.ok(task.get());
		}catch(Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	public ResponseEntity<?> create(TaskForm taskForm) {
		try {
			Task entity = taskForm.toEntity(taskForm);
			Optional<User> userOP = userRepository.findById(taskForm.getUserId());
			if(userOP.isEmpty()) {
				return ResponseEntity.notFound().build();
			}
			entity.setUser(userOP.get());
			taskRepository.save(entity);
			return ResponseEntity.created(null).build();
		}catch(Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	public ResponseEntity<Task> updateTask(Long id, TaskForm taskForm) {
		try {
			boolean exists = taskRepository.existsById(id);
			if(!exists) {
				return ResponseEntity.notFound().build();
			}
			Optional<User> userOP = userRepository.findById(taskForm.getUserId());
			if(userOP.isEmpty()) {
				return ResponseEntity.notFound().build();
			}
			Task task = taskForm.toEntity(taskForm);
			task.setUser(userOP.get());
			task = taskRepository.save(task);
			return ResponseEntity.ok(task);
		}catch(Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	public ResponseEntity<?> deleteTask(Long id) {
		try {
			taskRepository.deleteById(id);
			return ResponseEntity.ok(null);
		}catch(Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

}
