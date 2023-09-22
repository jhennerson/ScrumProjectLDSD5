package com.ifsp.scrumProjectLDSD5.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ifsp.scrumProjectLDSD5.dto.TaskDTO;
import com.ifsp.scrumProjectLDSD5.form.TaskForm;
import com.ifsp.scrumProjectLDSD5.model.Task;
import com.ifsp.scrumProjectLDSD5.service.TaskService;


@RestController
@RequestMapping("/task")
@CrossOrigin("*")
public class TaskController {

	
	@Autowired(required = true)
	private TaskService taskService;
	
	@GetMapping
	@Cacheable(value = "task")
	public ResponseEntity<List<Task>> getAllTasks(){
		return taskService.getAllTasks();
	};
	
	@GetMapping("/{id}")
	@Cacheable(value = "task")
	public ResponseEntity<Task> getTaskById(@PathVariable Long id){
		return taskService.getTaskById(id);
	};
	
	
	@PostMapping()
	@CacheEvict(value = "task" , allEntries = true)
	public ResponseEntity<?> create(@RequestBody @Validated TaskForm taskForm){
		return taskService.create(taskForm);
	};
	

	
	@PatchMapping("/{id}")
	@CacheEvict(value = "task" , allEntries = true)
	public ResponseEntity<Task> updateTask(@PathVariable Long id ,@RequestBody @Validated TaskForm taskForm){
		return taskService.updateTask(id,taskForm);
	};
	
	@DeleteMapping("/{id}")
	@CacheEvict(value = "task" , allEntries = true)
	public ResponseEntity<?> deleteTask(@PathVariable Long id){
		return taskService.deleteTask(id);
	}

}
	

