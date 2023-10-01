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

import com.ifsp.scrumProjectLDSD5.entity.UserStory;
import com.ifsp.scrumProjectLDSD5.repository.UserStoryRepository;


@Component
public class UserStoryJPA {
	
	@Autowired
	private UserStoryRepository uhr;
	
    
    public UserStory create(UserStory uh){
    	return uhr.save(uh);
    }
    
    
    public List<UserStory> list(){
    	return uhr.findAll();
    }
    
    
    
    public Optional<UserStory> findById(Long id){
    	return uhr.findById(id);
    }
    
    
    public UserStory update(UserStory entity){
    	return uhr.save(entity);
    }
    
    
    public void delete(Long id){
    	uhr.deleteById(id);
    }


	public boolean exist(UserStory entity) {
		return uhr.existsById(entity.getId());
		
	}
}
