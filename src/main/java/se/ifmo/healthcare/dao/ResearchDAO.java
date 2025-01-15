package se.ifmo.healthcare.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import se.ifmo.healthcare.models.Research;

import java.util.List;

@Repository
public class ResearchDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void save(Research research) {
        entityManager.persist(research);
    }

    public Research findById(Long id) {
        return entityManager.find(Research.class, id);
    }

    public List<Research> findAll() {
        return entityManager.createQuery("SELECT d FROM Research d", Research.class).getResultList();
    }

    @Transactional
    public void delete(Long id) {
        Research research = findById(id);
        if (research != null) {
            entityManager.remove(research);
        }
    }
}
