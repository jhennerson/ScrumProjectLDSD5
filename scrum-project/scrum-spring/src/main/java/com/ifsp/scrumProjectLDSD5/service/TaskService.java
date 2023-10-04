package com.ifsp.scrumProjectLDSD5.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.ifsp.scrumProjectLDSD5.JPA.TaskJPA;
import com.ifsp.scrumProjectLDSD5.JPA.UserJPA;
import com.ifsp.scrumProjectLDSD5.dto.TaskDTO;
import com.ifsp.scrumProjectLDSD5.dto.TaskErrorDTO;
import com.ifsp.scrumProjectLDSD5.dto.UserDTO;
import com.ifsp.scrumProjectLDSD5.dto.mixin.UserDTOPasswordEmailMixin;
import com.ifsp.scrumProjectLDSD5.entity.Task;
import com.ifsp.scrumProjectLDSD5.entity.User;
import com.ifsp.scrumProjectLDSD5.exception.EmptyRecordException;
import com.ifsp.scrumProjectLDSD5.exception.UsuarioNaoEncontradoException;
import com.ifsp.scrumProjectLDSD5.form.TaskForm;
import com.ifsp.scrumProjectLDSD5.interfaces.ITask;

@Service
public class TaskService {

	@Autowired
	private TaskJPA taskJPA;
	
	@Autowired
	private UserJPA userJPA;
	
	private ObjectMapper om; 

    public TaskService() {
    	om = new ObjectMapper();
    	om.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    	om.addMixIn(UserDTO.class, UserDTOPasswordEmailMixin.class);
    }	
	
	public ResponseEntity<?> getAllTasks(){

		List<Task> tasks = taskJPA.getAllTask();
		if(tasks.isEmpty()) {
			throw new EmptyRecordException();
		}
					
		List<TaskDTO> tasksDTO = om.convertValue(tasks, new TypeReference<List<TaskDTO>>() {});
					
			
		return ResponseEntity.status(HttpStatus.OK).body(tasksDTO);
	}
	
	public ResponseEntity<ITask> findTaskById(Long id){
			Optional<Task> task = taskJPA.getTaskById(id);
			if(task.isEmpty()) {
				throw new EmptyRecordException(id);
			}
			TaskDTO taskDTO = om.convertValue(task, TaskDTO.class);
			return ResponseEntity.status(HttpStatus.OK).body(taskDTO);
	}
	
	public ResponseEntity<ITask> update(TaskForm taskForm){
			if(taskForm.getId()== null) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(TaskErrorDTO.set(HttpStatus.BAD_REQUEST,"Valor do ID não pode ser nulo ","api/tasks"));
			}
			boolean tarefaExiste = taskJPA.existById(taskForm.getId());
			if(!tarefaExiste) {
				throw new EmptyRecordException(taskForm.getId());
			}
			Task task = taskForm.toEntity(taskForm);
			if(!(taskForm.getUserId() == null)) {
				Optional<User> userById = userJPA.getUserById(taskForm.getUserId());
				if(userById.isEmpty()) {
					throw new UsuarioNaoEncontradoException(taskForm.getUserId());
				}
				task.setUser(userById.get());
				
			}
			Task updateTask = taskJPA.updateTask(task);
			TaskDTO taskDTO = om.convertValue(updateTask, TaskDTO.class);;
			return ResponseEntity.status(HttpStatus.OK).body(taskDTO);
	}
	
	public ResponseEntity<ITask> create(TaskForm taskForm){
		Task entity = taskForm.toEntity(taskForm);
		entity.setUser(null);
		if(!(taskForm.getUserId() == null)) {
			Optional<User> userById = userJPA.getUserById(taskForm.getUserId());
			if(userById.isEmpty()) {
				throw new UsuarioNaoEncontradoException(taskForm.getUserId());
			}
			entity.setUser(userById.get());
		}
		Task create = taskJPA.create(entity);
		TaskDTO convertValue = om.convertValue(create, TaskDTO.class);
		return ResponseEntity.status(HttpStatus.CREATED).body(convertValue);

	}

	public ResponseEntity<ITask> deleteById(Long id) {
			Optional<Task> taskById = taskJPA.getTaskById(id);
			if(taskById.isEmpty()) {
				throw new EmptyRecordException(id);
			}
			taskJPA.deleteById(id);
			TaskDTO task = om.convertValue(taskById.get(), TaskDTO.class);
			return ResponseEntity.status(HttpStatus.OK).body(task);
			
	}	
}
