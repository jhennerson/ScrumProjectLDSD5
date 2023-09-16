package com.ifsp.scrumProjectLDSD5.repository;

import com.ifsp.scrumProjectLDSD5.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}