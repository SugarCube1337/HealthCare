package se.ifmo.healthcare.mappers;

import se.ifmo.healthcare.models.Person;
import se.ifmo.healthcare.dto.PersonDTO;

import java.time.LocalDateTime;

public class PersonMapper {

    public static PersonDTO toDTO(Person person) {
        if (person == null) return null;
        PersonDTO dto = new PersonDTO();
        dto.id = person.getId();
        dto.name = person.getName();
        dto.surname = person.getSurname();
        dto.gender = person.getGender();
        dto.birthDate = person.getBirthDate().toString();
        dto.contactInfo = person.getContactInfo();
        return dto;
    }

    public static Person toEntity(PersonDTO dto) {
        if (dto == null) return null;
        Person person = new Person();
        person.setId(dto.id);
        person.setName(dto.name);
        person.setSurname(dto.surname);
        person.setGender(dto.gender);
        person.setBirthDate(LocalDateTime.parse(dto.birthDate));
        person.setContactInfo(dto.contactInfo);
        return person;
    }
}