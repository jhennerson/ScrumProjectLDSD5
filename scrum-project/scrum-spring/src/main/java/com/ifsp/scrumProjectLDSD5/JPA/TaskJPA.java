package com.ifsp.scrumProjectLDSD5.JPA;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.ifsp.scrumProjectLDSD5.dto.TaskDTO;
import com.ifsp.scrumProjectLDSD5.dto.TaskErrorDTO;
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
	
	public ResponseEntity getAllTasks() {
		try {
			List<Task> findAll = taskRepository.findAll();
			if(findAll.isEmpty()) {
				return TaskErrorDTO.set(HttpStatus.NOT_FOUND, "Não foi encontrado nenhum registro", "/api/tasks").toResponseEntity();
			}
			return ResponseEntity.ok(findAll);
		}catch(Exception e) {
			return TaskErrorDTO.set(HttpStatus.NOT_FOUND, "Erro Interno", "/api/tasks",e.getMessage()).toResponseEntity();
		}
	}

	public ResponseEntity getTaskById(Long id) {
		try {
			Optional<Task> task = taskRepository.findById(id);
			if(task.isEmpty()) {
				return TaskErrorDTO.set(HttpStatus.NOT_FOUND, "Não foi encontrado nenhum registro", "/api/tasks").toResponseEntity();
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
				return TaskErrorDTO.set(HttpStatus.NOT_FOUND, "Usuario com ID " + taskForm.getUserId() + " Não encontrado", "/api/tasks").toResponseEntity();
			}
			entity.setUser(userOP.get());
			Task save = taskRepository.save(entity);
			return ResponseEntity.status(HttpStatus.CREATED).body(save);
		}catch(Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	public ResponseEntity updateTask(Long id, TaskForm taskForm) {
		try {
			boolean exists = taskRepository.existsById(id);
			if(!exists) {
				return TaskErrorDTO.set(HttpStatus.NOT_FOUND, "Tarefa com ID " + taskForm.getId() + " Não encontrada", "/api/tasks").toResponseEntity();
			}
			Optional<User> userOP = userRepository.findById(taskForm.getUserId());
			if(userOP.isEmpty()) {
				return TaskErrorDTO.set(HttpStatus.NOT_FOUND, "Usuario com ID " + taskForm.getUserId() + " Não encontrado", "/api/tasks").toResponseEntity();
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
