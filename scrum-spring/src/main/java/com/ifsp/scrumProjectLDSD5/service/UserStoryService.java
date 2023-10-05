package com.ifsp.scrumProjectLDSD5.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.ifsp.scrumProjectLDSD5.JPA.UserStoryJPA;
import com.ifsp.scrumProjectLDSD5.JPA.UserJPA;
import com.ifsp.scrumProjectLDSD5.dto.TaskDTO;
import com.ifsp.scrumProjectLDSD5.dto.UserDTO;
import com.ifsp.scrumProjectLDSD5.dto.UserStoryDTO;
import com.ifsp.scrumProjectLDSD5.dto.mixin.UserDTOPasswordEmailMixin;
import com.ifsp.scrumProjectLDSD5.entity.User;
import com.ifsp.scrumProjectLDSD5.entity.UserStory;
import com.ifsp.scrumProjectLDSD5.exception.EmptyRecordException;
import com.ifsp.scrumProjectLDSD5.exception.UsuarioNaoEncontradoException;
import com.ifsp.scrumProjectLDSD5.form.UserStoryForm;
import com.ifsp.scrumProjectLDSD5.interfaces.IUserStory;

@Service
public class UserStoryService {

	@Autowired
	private UserStoryJPA userStoryJPA;
	
	@Autowired
	private UserJPA userJPA;
	
	private ObjectMapper om; 

    public UserStoryService() {
    	om = new ObjectMapper();
    	om.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    	om.addMixIn(UserDTO.class, UserDTOPasswordEmailMixin.class);
    }
	
	

    public ResponseEntity<IUserStory> create(UserStoryForm form){
    	
    	
    	UserStory entity = form.toEntity();
    	if(!(form.getUserId() == null)) {
    	  	Optional<User> userOP = userJPA.getUserById(form.getUserId());
        	if(userOP.isEmpty()) {
        		throw new UsuarioNaoEncontradoException(form.getUserId());
        	}
        	entity.setAssignee(userOP.get());
    	}
  
    	if(!(form.getReporterId() == null)) {
        	Optional<User> reporterOP = userJPA.getUserById(form.getReporterId()); 
        	if(reporterOP.isEmpty()) {
        		throw new UsuarioNaoEncontradoException(form.getReporterId());
        	}
        	entity.setReporter(reporterOP.get());
    	}
    	
    	UserStory create = userStoryJPA.create(entity);
    	UserStoryDTO dto = om.convertValue(create, UserStoryDTO.class);
    	
    	
    	return ResponseEntity.status(HttpStatus.OK).body(dto);
    }
    
//    	throw new UsuarioNaoEncontradoException(1l);

    public ResponseEntity<?> list(){
    	List<UserStory> list = userStoryJPA.list();
    	if(list.isEmpty()) {
    		throw new EmptyRecordException();
    	}
    	List<UserStoryDTO> listDTO = om.convertValue(list, new TypeReference<List<UserStoryDTO>>() {});
    	return ResponseEntity.status(HttpStatus.OK).body(listDTO);
    }
    
    
    public ResponseEntity<IUserStory> findById(Long id){
    	Optional<UserStory> userStory = userStoryJPA.findById(id);
    	if(userStory.isEmpty()) {
    		throw new EmptyRecordException(id);
    	}
    	UserStoryDTO uhDTO = om.convertValue(userStory.get(), UserStoryDTO.class);
    	return ResponseEntity.status(HttpStatus.OK).body(uhDTO);
    }
    
    public ResponseEntity<IUserStory> update(UserStoryForm form){
    	UserStory entity = form.toEntity();
    	if(!(form.getUserId() == null)) {
    	  	Optional<User> userOP = userJPA.getUserById(form.getUserId());
        	if(userOP.isEmpty()) {
        		throw new UsuarioNaoEncontradoException(form.getUserId());
        	}
        	entity.setAssignee(userOP.get());
    	}
  
    	if(!(form.getReporterId() == null)) {
        	Optional<User> reporterOP = userJPA.getUserById(form.getReporterId()); 
        	if(reporterOP.isEmpty()) {
        		throw new UsuarioNaoEncontradoException(form.getReporterId());
        	}
        	entity.setReporter(reporterOP.get());
    	}
    	
    	boolean exist = userStoryJPA.exist(entity);
    	if(!exist) {
    		throw new EmptyRecordException(entity.getId());
    	}
    	UserStory update = userStoryJPA.update(entity);
    	UserStoryDTO uhDTO = om.convertValue(update, UserStoryDTO.class);
    	return ResponseEntity.status(HttpStatus.OK).body(uhDTO);
    	
    }
    
    public ResponseEntity<IUserStory> delete(Long id){
	  	Optional<UserStory> usOP = userStoryJPA.findById(id);
	  	if(usOP.isEmpty()) {
	  		throw new UsuarioNaoEncontradoException(id);
	  	}
	  	UserStoryDTO uhDTO = om.convertValue(usOP.get(), UserStoryDTO.class);
    	userStoryJPA.delete(id);
    	return ResponseEntity.status(HttpStatus.OK).body(uhDTO);
    }
}
