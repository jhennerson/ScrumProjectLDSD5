package com.ifsp.scrumProjectLDSD5.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ifsp.scrumProjectLDSD5.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {

}