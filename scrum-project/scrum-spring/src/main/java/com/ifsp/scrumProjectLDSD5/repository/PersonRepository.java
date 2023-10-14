package com.ifsp.scrumProjectLDSD5.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ifsp.scrumProjectLDSD5.entity.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {

}
