package se.ifmo.healthcare.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import se.ifmo.healthcare.models.Research;
import se.ifmo.healthcare.models.WorkWithBiomaterials;

import java.util.List;

@Repository
public class WorkWithBiomaterialsDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void save(WorkWithBiomaterials workWithBiomaterials) {
        entityManager.persist(workWithBiomaterials);
    }

    public WorkWithBiomaterials findById(Long id) {
        return entityManager.find(WorkWithBiomaterials.class, id);
    }

    public List<WorkWithBiomaterials> findAll() {
        return entityManager.createQuery("SELECT d FROM WorkWithBiomaterials d", WorkWithBiomaterials.class).getResultList();
    }

    @Transactional
    public void delete(Long id) {
        WorkWithBiomaterials workWithBiomaterials = findById(id);
        if (workWithBiomaterials != null) {
            entityManager.remove(workWithBiomaterials);
        }
    }
}
