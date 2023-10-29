package com.ifsp.scrumProjectLDSD5.mapper;

import org.springframework.stereotype.Component;

import com.ifsp.scrumProjectLDSD5.dto.PersonDTO;
import com.ifsp.scrumProjectLDSD5.entity.Person;

@Component
public class PersonMapper {
    
    public PersonDTO toDTO(Person person) {
        if(person == null) {
            return null;
        }

        return new PersonDTO(
            person.getId(),
            person.getUsername(),
            person.getEmail()
        );
    }
    
    public Person toEntity(PersonDTO personDTO) {
        if(personDTO == null) {
            return null;
        }

        Person person = new Person();

        if(personDTO.id() != null) {
            person.setId(personDTO.id());
        }

        person.setUsername(personDTO.email());
        person.setEmail(personDTO.email());

        return person;
    }
}
