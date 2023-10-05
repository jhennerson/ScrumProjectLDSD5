package com.ifsp.scrumProjectLDSD5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ifsp.scrumProjectLDSD5.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
