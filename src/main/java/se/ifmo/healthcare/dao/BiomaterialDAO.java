package se.ifmo.healthcare.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import se.ifmo.healthcare.models.Biomaterial;

import java.util.List;

@Repository
public class BiomaterialDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void save(Biomaterial biomaterial) {
        entityManager.persist(biomaterial);
    }

    public Biomaterial findById(Long id) {
        return entityManager.find(Biomaterial.class, id);
    }

    public List<Biomaterial> findAll() {
        return entityManager.createQuery("SELECT d FROM Biomaterial d", Biomaterial.class).getResultList();
    }

    @Transactional
    public List<Biomaterial> findExpired() {
        return entityManager.createQuery(
                "SELECT d FROM Biomaterial d WHERE TRIM(d.status) = 'Sent to Nurse'", Biomaterial.class
        ).getResultList();
    }

    @Transactional
    public void delete(Long id) {
        Biomaterial biomaterial = findById(id);
        if (biomaterial != null) {
            entityManager.remove(biomaterial);
        }
    }
}
