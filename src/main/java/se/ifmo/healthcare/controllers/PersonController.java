package se.ifmo.healthcare.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.ifmo.healthcare.dto.PersonDTO;
import se.ifmo.healthcare.services.PersonService;

import java.util.List;

@RestController
@RequestMapping("/api/persons")
public class PersonController {

    @Autowired
    private PersonService personService;

    @PostMapping
    public ResponseEntity<String> createPerson(@RequestBody PersonDTO personDTO) {
        personService.createPerson(personDTO);
        return ResponseEntity.ok("Person created successfully");
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonDTO> getPersonById(@PathVariable Long id) {
        PersonDTO personDTO = personService.getPersonById(id);
        if (personDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(personDTO);
    }

    @GetMapping
    public List<PersonDTO> getAllPersons() {
        return personService.getAllPersons();
    }

    @PutMapping
    public ResponseEntity<String> updatePerson(@RequestBody PersonDTO personDTO) {
        personService.updatePerson(personDTO);
        return ResponseEntity.ok("Person updated successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePerson(@PathVariable Long id) {
        personService.deletePerson(id);
        return ResponseEntity.ok("Person deleted successfully");
    }
}

