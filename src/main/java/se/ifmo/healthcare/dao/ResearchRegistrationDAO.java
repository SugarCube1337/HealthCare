package se.ifmo.healthcare.dao;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import se.ifmo.healthcare.models.ResearchRegistration;

import java.util.List;

@Repository
public class ResearchRegistrationDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void save(ResearchRegistration researchRegistration) {
        entityManager.persist(researchRegistration);
    }

    public ResearchRegistration findById(Long id) {
        return entityManager.find(ResearchRegistration.class, id);
    }

    public List<ResearchRegistration> findAll() {
        return entityManager.createQuery("SELECT d FROM ResearchRegistration d", ResearchRegistration.class).getResultList();
    }

    @Transactional
    public void delete(Long id) {
        ResearchRegistration researchRegistration = findById(id);
        if (researchRegistration != null) {
            entityManager.remove(researchRegistration);
        }
    }
}
