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
import com.ifsp.scrumProjectLDSD5.JPA.UserHistoryJPA;
import com.ifsp.scrumProjectLDSD5.JPA.UserJPA;
import com.ifsp.scrumProjectLDSD5.dto.TaskDTO;
import com.ifsp.scrumProjectLDSD5.dto.UserDTO;
import com.ifsp.scrumProjectLDSD5.dto.UserHistoryDTO;
import com.ifsp.scrumProjectLDSD5.dto.mixin.UserDTOPasswordEmailMixin;
import com.ifsp.scrumProjectLDSD5.entity.User;
import com.ifsp.scrumProjectLDSD5.entity.UserHistory;
import com.ifsp.scrumProjectLDSD5.exception.EmptyRecordException;
import com.ifsp.scrumProjectLDSD5.exception.UsuarioNaoEncontradoException;
import com.ifsp.scrumProjectLDSD5.form.UserHistoryForm;
import com.ifsp.scrumProjectLDSD5.interfaces.IUserHistory;

@Service
public class UserHistoryService {

	@Autowired
	private UserHistoryJPA userHistoryJPA;
	
	@Autowired
	private UserJPA userJPA;
	
	private ObjectMapper om; 

    public UserHistoryService() {
    	om = new ObjectMapper();
    	om.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    	om.addMixIn(UserDTO.class, UserDTOPasswordEmailMixin.class);
    }
	
	

    public ResponseEntity<IUserHistory> create(UserHistoryForm form){
    	
    	
    	UserHistory entity = form.toEntity();
    	if(!(form.getUserId() == null)) {
    	  	Optional<User> userOP = userJPA.getUserById(form.getUserId());
        	if(userOP.isEmpty()) {
        		throw new UsuarioNaoEncontradoException(form.getUserId());
        	}
        	entity.setUser(userOP.get());
    	}
  
    	if(!(form.getReporterId() == null)) {
        	Optional<User> reporterOP = userJPA.getUserById(form.getReporterId()); 
        	if(reporterOP.isEmpty()) {
        		throw new UsuarioNaoEncontradoException(form.getReporterId());
        	}
        	entity.setReporter(reporterOP.get());
    	}
    	

    	
    	UserHistory create = userHistoryJPA.create(entity);
    	UserHistoryDTO dto = om.convertValue(create, UserHistoryDTO.class);
    	
    	
    	return ResponseEntity.status(HttpStatus.OK).body(dto);
    }
    
//    	throw new UsuarioNaoEncontradoException(1l);

    public ResponseEntity<?> list(){
    	List<UserHistory> list = userHistoryJPA.list();
    	if(list.isEmpty()) {
    		throw new EmptyRecordException();
    	}
    	List<UserHistoryDTO> listDTO = om.convertValue(list, new TypeReference<List<UserHistoryDTO>>() {});
    	return ResponseEntity.status(HttpStatus.OK).body(listDTO);
    }
    
    
    public ResponseEntity<IUserHistory> findById(Long id){
    	Optional<UserHistory> userHistory = userHistoryJPA.findById(id);
    	if(userHistory.isEmpty()) {
    		throw new EmptyRecordException(id);
    	}
    	UserHistoryDTO uhDTO = om.convertValue(userHistory.get(), UserHistoryDTO.class);
    	return ResponseEntity.status(HttpStatus.OK).body(uhDTO);
    }
    
    public ResponseEntity<IUserHistory> update(UserHistoryForm form){
    	UserHistory entity = form.toEntity();
    	if(!(form.getUserId() == null)) {
    	  	Optional<User> userOP = userJPA.getUserById(form.getUserId());
        	if(userOP.isEmpty()) {
        		throw new UsuarioNaoEncontradoException(form.getUserId());
        	}
        	entity.setUser(userOP.get());
    	}
  
    	if(!(form.getReporterId() == null)) {
        	Optional<User> reporterOP = userJPA.getUserById(form.getReporterId()); 
        	if(reporterOP.isEmpty()) {
        		throw new UsuarioNaoEncontradoException(form.getReporterId());
        	}
        	entity.setReporter(reporterOP.get());
    	}
    	
    	boolean exist = userHistoryJPA.exist(entity);
    	if(!exist) {
    		throw new EmptyRecordException(entity.getId());
    	}
    	UserHistory update = userHistoryJPA.update(entity);
    	UserHistoryDTO uhDTO = om.convertValue(update, UserHistoryDTO.class);
    	return ResponseEntity.status(HttpStatus.OK).body(uhDTO);
    	
    }
    
    public ResponseEntity<IUserHistory> delete(Long id){
	  	Optional<UserHistory> usOP = userHistoryJPA.findById(id);
	  	if(usOP.isEmpty()) {
	  		throw new UsuarioNaoEncontradoException(id);
	  	}
	  	UserHistoryDTO uhDTO = om.convertValue(usOP.get(), UserHistoryDTO.class);
    	userHistoryJPA.delete(id);
    	return ResponseEntity.status(HttpStatus.OK).body(uhDTO);
    }





}
