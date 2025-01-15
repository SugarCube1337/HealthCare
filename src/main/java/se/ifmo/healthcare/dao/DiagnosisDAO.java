package se.ifmo.healthcare.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import se.ifmo.healthcare.models.*;

import java.util.List;

@Repository
public class DiagnosisDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void save(Diagnosis diagnosis) {
        entityManager.persist(diagnosis);
    }

    public Diagnosis findById(Long id) {
        return entityManager.find(Diagnosis.class, id);
    }

    public List<Diagnosis> findAll() {
        return entityManager.createQuery("SELECT d FROM Diagnosis d", Diagnosis.class).getResultList();
    }

    @Transactional
    public void delete(Long id) {
        Diagnosis diagnosis = findById(id);
        if (diagnosis != null) {
            entityManager.remove(diagnosis);
        }
    }
}