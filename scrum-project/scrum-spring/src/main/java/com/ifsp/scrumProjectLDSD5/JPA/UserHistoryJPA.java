package com.ifsp.scrumProjectLDSD5.JPA;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.ifsp.scrumProjectLDSD5.entity.UserHistory;
import com.ifsp.scrumProjectLDSD5.repository.UserHistoryRepository;


@Component
public class UserHistoryJPA {
	
	@Autowired
	private UserHistoryRepository uhr;
	
    
    public UserHistory create(){
    	return null;
    }
    
    
    public List<UserHistory> list(){
    	return null;
    }
    
    
    
    public UserHistory findById(){
    	return null;
    }
    
    
    public UserHistory update(){
    	return null;
    }
    
    
    public UserHistory delete(){
    	return null;
    }
}
