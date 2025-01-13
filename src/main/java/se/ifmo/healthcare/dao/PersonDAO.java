package se.ifmo.healthcare.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import se.ifmo.healthcare.models.Person;

import java.util.List;

@Repository
public class PersonDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void save(Person person) {
        entityManager.persist(person);
    }

    @Transactional
    public Person update(Person person) {
        return entityManager.merge(person);
    }

    public Person findById(Long id) {
        return entityManager.find(Person.class, id);
    }

    public List<Person> findAll() {
        return entityManager.createQuery("SELECT p FROM Person p", Person.class).getResultList();
    }

    @Transactional
    public void delete(Long id) {
        Person person = findById(id);
        if (person != null) {
            entityManager.remove(person);
        }
    }
}