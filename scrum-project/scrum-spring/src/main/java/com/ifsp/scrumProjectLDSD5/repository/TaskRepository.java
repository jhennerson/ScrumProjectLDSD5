package com.ifsp.scrumProjectLDSD5.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ifsp.scrumProjectLDSD5.entity.Task;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, String> {
    List<Task> findBySprintId(String sprintId);
}
