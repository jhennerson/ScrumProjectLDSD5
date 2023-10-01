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

import com.ifsp.scrumProjectLDSD5.exception.UsuarioNaoEncontradoException;
import com.ifsp.scrumProjectLDSD5.form.UserHistoryForm;
import com.ifsp.scrumProjectLDSD5.interfaces.IUserHistory;
import com.ifsp.scrumProjectLDSD5.service.UserHistoryService;
import com.ifsp.scrumProjectLDSD5.service.UserService;

import jakarta.servlet.http.HttpServletRequest;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/userHistory")
public class UserHistoryController {
	
	
    @Autowired(required=true)
    private UserHistoryService userHistoryService;
    
    
    @PostMapping()
    public ResponseEntity<IUserHistory> create(@RequestBody @Validated UserHistoryForm userHistoryForm){
    	return userHistoryService.create(userHistoryForm);
    }
    
    @GetMapping
    public ResponseEntity<?> list(){
    	return userHistoryService.list();
    }
    
    
    @GetMapping("/{id}")
    public ResponseEntity<IUserHistory> findById(@PathVariable Long id){
    	return userHistoryService.findById();
    }
    
    @PutMapping()
    public ResponseEntity<IUserHistory> update(@RequestBody @Validated UserHistoryForm userHistoryForm){
    	return userHistoryService.update(userHistoryForm);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<IUserHistory> delete(@PathVariable Long id){
    	return userHistoryService.delete();
    }
    
    
    
}
