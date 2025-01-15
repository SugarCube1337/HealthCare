package se.ifmo.healthcare.mappers;

import se.ifmo.healthcare.models.Person;
import se.ifmo.healthcare.dto.PersonDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class PersonMapper {

    public static PersonDTO toDTO(Person person) {
        if (person == null) return null;
        PersonDTO dto = new PersonDTO();
        dto.setId(person.getId());
        dto.setName(person.getName());
        dto.setSurname(person.getSurname());
        dto.setGender(person.getGender());
        dto.setContactInfo(person.getContactInfo());
        dto.setBirthDate(person.getBirthDate().toString());
        return dto;
    }

    public static Person toEntity(PersonDTO dto) {
        if (dto == null) return null;
        Person person = new Person();
        person.setId(dto.getId());
        person.setName(dto.getName());
        person.setSurname(dto.getSurname());
        person.setGender(dto.getGender());
        person.setContactInfo(dto.getContactInfo());
        person.setBirthDate(LocalDate.parse(dto.getBirthDate()));
        return person;
    }
}