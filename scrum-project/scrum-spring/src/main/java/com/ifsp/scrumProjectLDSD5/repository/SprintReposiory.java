package com.ifsp.scrumProjectLDSD5.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ifsp.scrumProjectLDSD5.entity.Sprint;

public interface SprintReposiory extends JpaRepository<Sprint, Long> {
    
}
