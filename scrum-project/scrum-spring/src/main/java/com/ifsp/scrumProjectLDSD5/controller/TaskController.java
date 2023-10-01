package com.ifsp.scrumProjectLDSD5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ifsp.scrumProjectLDSD5.form.TaskForm;
import com.ifsp.scrumProjectLDSD5.interfaces.ITask;
import com.ifsp.scrumProjectLDSD5.service.TaskService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/tasks")
public class TaskController {

	
	@Autowired
	private TaskService taskService;
	
	@GetMapping
	public ResponseEntity<?> getAllTasks(){
		return taskService.getAllTasks();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ITask> getTaskById(@PathVariable Long id){
		return taskService.findTaskById(id);
	}
	
	@PostMapping
	public ResponseEntity<ITask> createTask(@RequestBody @Validated TaskForm taskForm){
		return taskService.create(taskForm);
	}
	
	@PutMapping()
	public ResponseEntity<ITask> updateTask(@RequestBody @Validated TaskForm taskForm){
		return taskService.update(taskForm);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ITask> deleteTaskById(@PathVariable Long id){
		return taskService.deleteById(id);
	}
}
