package com.ifsp.scrumProjectLDSD5.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ifsp.scrumProjectLDSD5.entity.UserStory;

public interface UserStoryRepository extends JpaRepository<UserStory, Long>{

}
