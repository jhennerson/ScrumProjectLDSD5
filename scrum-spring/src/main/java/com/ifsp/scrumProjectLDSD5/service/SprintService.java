package com.ifsp.service;

import com.ifsp.model.Sprint;
import com.ifsp.repository.SprintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SprintService {

    @Autowired
    private SprintRepository sprintRepository;

    public Sprint createSprint(Sprint sprint){
        return sprintRepository.save(sprint);
    }

    public Sprint updateSprint(Long id, Sprint updateSprint){
        if(sprintRepository.existsById(id)){
            updateSprint.setId(id);
            return sprintRepository.save(updateSprint);
        }
        return null;
    }

    public List<Sprint> getAllSprints(){
        return sprintRepository.findAll();
    }

    public Sprint getSprintById(Long id){
        return sprintRepository.findById(id).orElse(null);
    }

    public boolean deleteSprint(Long id){
        sprintRepository.deleteById(id);
        return false;
    }

}
