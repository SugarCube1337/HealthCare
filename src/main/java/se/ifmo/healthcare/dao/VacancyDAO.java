package se.ifmo.healthcare.dao;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import se.ifmo.healthcare.models.Vacancy;

import java.util.List;

@Repository
public class VacancyDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void save(Vacancy vacancy) {
        entityManager.persist(vacancy);
    }

    @Transactional
    public Vacancy update(Vacancy vacancy) {
        return entityManager.merge(vacancy);
    }

    public Vacancy findById(Long id) {
        return entityManager.find(Vacancy.class, id);
    }

    public List<Vacancy> findAll() {
        return entityManager.createQuery("SELECT v FROM Vacancy v", Vacancy.class).getResultList();
    }

    @Transactional
    public void delete(Long id) {
        Vacancy vacancy = findById(id);
        if (vacancy != null) {
            entityManager.remove(vacancy);
        }
    }
}