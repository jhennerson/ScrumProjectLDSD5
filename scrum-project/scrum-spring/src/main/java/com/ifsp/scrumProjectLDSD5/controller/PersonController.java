package com.ifsp.scrumProjectLDSD5.controller;

import com.ifsp.scrumProjectLDSD5.dto.PersonDTO;
import com.ifsp.scrumProjectLDSD5.service.PersonService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/api/persons")
public class PersonController {
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public List<PersonDTO> list() {
        return personService.list();
    }

    @GetMapping("/{id}")
    public PersonDTO findById(@PathVariable @NotNull @Positive Long id) {
        return personService.findById(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public PersonDTO create(@RequestBody @Valid @NotNull PersonDTO person) {
        return personService.create(person);
    }

    @PatchMapping("/{id}")
    public PersonDTO update(@PathVariable @NotNull @Positive Long id, @RequestBody @Valid @NotNull PersonDTO person) {
        return personService.update(id, person);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @NotNull @Positive Long id) {
        personService.delete(id);
    }
}


