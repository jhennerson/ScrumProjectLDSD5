package com.ifsp.scrumProjectLDSD5.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.ifsp.scrumProjectLDSD5.dto.PersonDTO;
import com.ifsp.scrumProjectLDSD5.exception.RecordNotFoundException;
import com.ifsp.scrumProjectLDSD5.mapper.PersonMapper;
import com.ifsp.scrumProjectLDSD5.repository.PersonRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Validated
@Service
public class PersonService {
    private final PersonRepository personRepository;
    private final PersonMapper personMapper;

    public PersonService(PersonRepository personRepository, PersonMapper personMapper) {
        this.personRepository = personRepository;
        this.personMapper = personMapper;
    }

    public List<PersonDTO> list() {
        return personRepository.findAll()
                               .stream()
                               .map(personMapper::toDTO)
                               .collect(Collectors.toList());
    }

    public PersonDTO findById(@NotNull @Positive Long id) {
        return personRepository.findById(id)
                               .map(personMapper::toDTO)
                               .orElseThrow(() -> new RecordNotFoundException(id));
    }

    public PersonDTO create(@Valid @NotNull PersonDTO person) {
        return personMapper.toDTO(personRepository.save(personMapper.toEntity(person)));
    }

    public PersonDTO update(@NotNull @Positive Long id, @Valid @NotNull PersonDTO personDTO) {
        return personRepository.findById(id)
                               .map(recordFound -> {
                                recordFound.setUsername(personDTO.username());
                                recordFound.setEmail(personDTO.email());
                                
                                return personMapper.toDTO(personRepository.save(recordFound));
                               }).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public void delete(@NotNull @Positive Long id) {
        personRepository.delete(personRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id)));
    }
}
