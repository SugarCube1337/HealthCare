package se.ifmo.healthcare.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.ifmo.healthcare.dao.PersonDAO;
import se.ifmo.healthcare.dto.PersonDTO;
import se.ifmo.healthcare.mappers.PersonMapper;
import se.ifmo.healthcare.models.Person;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonService {

    @Autowired
    private PersonDAO personDAO;

    public void createPerson(PersonDTO personDTO) {
        Person person = PersonMapper.toEntity(personDTO);
        personDAO.save(person);
    }

    public PersonDTO getPersonById(Long id) {
        Person person = personDAO.findById(id);
        return PersonMapper.toDTO(person);
    }

    public List<PersonDTO> getAllPersons() {
        return personDAO.findAll().stream()
                .map(PersonMapper::toDTO)
                .collect(Collectors.toList());
    }

    public void updatePerson(PersonDTO personDTO) {
        Person person = PersonMapper.toEntity(personDTO);
        personDAO.update(person);
    }

    public void deletePerson(Long id) {
        personDAO.delete(id);
    }
}