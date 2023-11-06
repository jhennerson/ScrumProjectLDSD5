package com.ifsp.scrumProjectLDSD5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.ifsp.scrumProjectLDSD5.entity.User;


public interface UserRepository extends JpaRepository<User, String> {
    UserDetails findByUsername(String username);
}
