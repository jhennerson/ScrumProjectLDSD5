package com.ifsp.scrumProjectLDSD5.JPA;

import java.util.List;
import java.util.Optional;

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
	
    
    public UserHistory create(UserHistory uh){
    	return uhr.save(uh);
    }
    
    
    public List<UserHistory> list(){
    	return uhr.findAll();
    }
    
    
    
    public Optional<UserHistory> findById(Long id){
    	return uhr.findById(id);
    }
    
    
    public UserHistory update(UserHistory entity){
    	return uhr.save(entity);
    }
    
    
    public void delete(Long id){
    	uhr.deleteById(id);
    }


	public boolean exist(UserHistory entity) {
		return uhr.existsById(entity.getId());
		
	}
}
