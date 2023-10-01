package com.ifsp.scrumProjectLDSD5.JPA;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ifsp.scrumProjectLDSD5.entity.Task;
import com.ifsp.scrumProjectLDSD5.entity.User;
import com.ifsp.scrumProjectLDSD5.form.TaskForm;
import com.ifsp.scrumProjectLDSD5.repository.TaskRepository;
import com.ifsp.scrumProjectLDSD5.repository.UserRepository;

@Component
public class TaskJPA {

	@Autowired
	private TaskRepository taskRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	
	public List<Task> getAllTask(){
		return taskRepository.findAll();
	}
	
	public Optional<Task> getTaskById(Long id) {
		Optional<Task> opEntity = taskRepository.findById(id);
		return opEntity;
	}
	
	public Task create(Task task) {
			Task save = taskRepository.save(task);
			return save;
	}
	
	public Task updateTask(Task task) {
		return taskRepository.save(task);
	}
	
	public boolean existById(Long id) {
		return taskRepository.existsById(id);
	}

	public void deleteById(Long id) {
		taskRepository.deleteById(id);
	}
}
