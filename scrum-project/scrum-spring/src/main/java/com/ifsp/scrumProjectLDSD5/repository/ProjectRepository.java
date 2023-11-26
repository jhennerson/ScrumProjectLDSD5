package com.ifsp.scrumProjectLDSD5.repository;

import com.ifsp.scrumProjectLDSD5.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;


public interface ProjectRepository extends JpaRepository <Project, String> {
    List<Project> findByTitle(String title);
}