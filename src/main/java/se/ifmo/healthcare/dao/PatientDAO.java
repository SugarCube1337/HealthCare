package se.ifmo.healthcare.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import se.ifmo.healthcare.models.Patient;
import se.ifmo.healthcare.models.User;

import java.util.List;

@Repository
public class PatientDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void save(Patient patient) {
        entityManager.persist(patient);
    }

    @Transactional
    public Patient update(Patient patient) {
        return entityManager.merge(patient);
    }

    public Patient findById(Long id) {
        return entityManager.find(Patient.class, id);
    }

    public List<Patient> findAll() {
        return entityManager.createQuery("SELECT p FROM Patient p", Patient.class).getResultList();
    }

    @Transactional
    public void delete(Long id) {
        Patient patient = findById(id);
        if (patient != null) {
            entityManager.remove(patient);
        }
    }

    public Patient findUPatientByPersonId(Long id) {
        try {
            return entityManager.createQuery("SELECT u FROM Patient u WHERE u.person.id = :id", Patient.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
