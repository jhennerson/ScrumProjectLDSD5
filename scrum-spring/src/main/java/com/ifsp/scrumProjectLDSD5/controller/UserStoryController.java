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
import com.ifsp.scrumProjectLDSD5.form.UserStoryForm;
import com.ifsp.scrumProjectLDSD5.interfaces.IUserStory;
import com.ifsp.scrumProjectLDSD5.service.UserStoryService;
import com.ifsp.scrumProjectLDSD5.service.UserService;

import jakarta.servlet.http.HttpServletRequest;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/user-stories")
public class UserStoryController {
	
	
    @Autowired(required=true)
    private UserStoryService userStoryService;
    
    
    @PostMapping()
    public ResponseEntity<IUserStory> create(@RequestBody @Validated UserStoryForm userStoryForm){
    	return userStoryService.create(userStoryForm);
    }
    
    @GetMapping
    public ResponseEntity<?> list(){
    	return userStoryService.list();
    }
    
    
    @GetMapping("/{id}")
    public ResponseEntity<IUserStory> findById(@PathVariable Long id){
    	return userStoryService.findById(id);
    }
    
    @PutMapping()
    public ResponseEntity<IUserStory> update(@RequestBody @Validated UserStoryForm userStoryForm){
    	return userStoryService.update(userStoryForm);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<IUserStory> delete(@PathVariable Long id){
    	return userStoryService.delete(id);
    }
    
    
    
}
