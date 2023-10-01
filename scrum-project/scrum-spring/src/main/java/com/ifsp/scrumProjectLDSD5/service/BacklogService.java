package com.ifsp.scrumProjectLDSD5.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifsp.scrumProjectLDSD5.entity.Backlog;
import com.ifsp.scrumProjectLDSD5.repository.BacklogRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BacklogService {

    @Autowired
    private BacklogRepository backlogRepository;

    public Backlog createBacklog(Backlog backlog){
        return backlogRepository.save(backlog);
    }

    public Optional<Backlog> getBacklogById(Long id){
        return backlogRepository.findById(id);
                //.orElseThrow(() -> new ResourceNotFoundException("Backlog não encontrado com o ID: " + id));
    }

    public Backlog updateBacklog(Long id, Backlog updatedBacklog) {
        Optional<Backlog> existingBacklogOptional = backlogRepository.findById(id);

        if (existingBacklogOptional.isPresent()) {
            Backlog existingBacklog = existingBacklogOptional.get();
            // Atualize os campos relevantes, como assignmentDate e endDate
            existingBacklog.setTitle(updatedBacklog.getTitle());
            existingBacklog.setAssignmentDate(updatedBacklog.getAssignmentDate());
            existingBacklog.setEndDate(updatedBacklog.getEndDate());
            return backlogRepository.save(existingBacklog);
        } else {
            return null; // Lidar com o cenário em que o Backlog não existe
        }
    }

    public void deleteBacklog(Long id) {
        backlogRepository.deleteById(id);
    }

    public List<Backlog> getAllBacklogs() {
        return backlogRepository.findAll();
    }


}
