package com.ifsp.scrumProjectLDSD5.repository;

import com.ifsp.scrumProjectLDSD5.entity.Sprint;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ifsp.scrumProjectLDSD5.entity.UserStory;

import java.util.List;

public interface UserStoryRepository extends JpaRepository<UserStory, String>{
    List<UserStory> findByProjectId(String projectId);
}
