package se.ifmo.healthcare.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import se.ifmo.healthcare.models.Biomaterial;
import se.ifmo.healthcare.models.Diagnosis;

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

    public List<Biomaterial> findExpired(){
        return entityManager.createQuery("SELECT d FROM Biomaterial d WHERE status = 'Sent to nurse'", Biomaterial.class).getResultList();
    }

    @Transactional
    public void delete(Long id) {
        Biomaterial biomaterial = findById(id);
        if (biomaterial != null) {
            entityManager.remove(biomaterial);
        }
    }
}
