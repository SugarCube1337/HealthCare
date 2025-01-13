package se.ifmo.healthcare.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import se.ifmo.healthcare.models.Patient;

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
}
