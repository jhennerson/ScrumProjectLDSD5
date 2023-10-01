package com.ifsp.scrumProjectLDSD5.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.ifsp.scrumProjectLDSD5.JPA.UserHistoryJPA;
import com.ifsp.scrumProjectLDSD5.dto.UserDTO;
import com.ifsp.scrumProjectLDSD5.dto.mixin.UserDTOPasswordEmailMixin;
import com.ifsp.scrumProjectLDSD5.exception.UsuarioNaoEncontradoException;
import com.ifsp.scrumProjectLDSD5.form.UserHistoryForm;
import com.ifsp.scrumProjectLDSD5.interfaces.IUserHistory;

@Service
public class UserHistoryService {

	@Autowired
	private UserHistoryJPA userHistoryJPA;
	private ObjectMapper objectMapper; 

    public UserHistoryService() {
        objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        objectMapper.addMixIn(UserDTO.class, UserDTOPasswordEmailMixin.class);
    }
	
	
	
    @PostMapping()
    public ResponseEntity<IUserHistory> create(UserHistoryForm userHistoryForm){
    	

    	return null;
    }
    
    @GetMapping
    public ResponseEntity<?> list(){
    	throw new UsuarioNaoEncontradoException(1l);
//    	return null;
    }
    
    
    @GetMapping
    public ResponseEntity<IUserHistory> findById(){
    	return null;
    }
    
    @PutMapping()
    public ResponseEntity<IUserHistory> update(UserHistoryForm userHistoryForm){
    	return null;
    }
    
    @DeleteMapping
    public ResponseEntity<IUserHistory> delete(){
    	return null;
    }
}
