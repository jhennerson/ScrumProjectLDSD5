package com.ifsp.scrumProjectLDSD5.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ifsp.scrumProjectLDSD5.entity.Sprint;

import java.util.List;

public interface SprintRepository extends JpaRepository<Sprint, String> {
    List<Sprint> findByProjectId(String projectId);
}
