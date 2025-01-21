package se.ifmo.healthcare.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import se.ifmo.healthcare.models.Candidate;
import se.ifmo.healthcare.models.StaffMember;

import java.util.List;

@Repository
public class CandidateDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void save(Candidate candidate) {
        entityManager.persist(candidate);
    }

    @Transactional
    public Candidate update(Candidate candidate) {
        return entityManager.merge(candidate);
    }

    public Candidate findById(Long id) {
        return entityManager.find(Candidate.class, id);
    }

    public List<Candidate> findAll() {
        return entityManager.createQuery("SELECT c FROM Candidate c", Candidate.class).getResultList();
    }

    @Transactional
    public void delete(Long id) {
        Candidate candidate = findById(id);
        if (candidate != null) {
            entityManager.remove(candidate);
        }
    }

    public List<Candidate> findByVacancyId(Long vacancyId) {
        return entityManager.createQuery(
                        "SELECT c FROM Candidate c JOIN c.vacancies v WHERE v.id = :vacancyId", Candidate.class)
                .setParameter("vacancyId", vacancyId)
                .getResultList();
    }
    public Candidate findCandidateByPersonId(Long id) {
        try {
            return entityManager.createQuery("SELECT c FROM Candidate c WHERE c.person.id = :id", Candidate.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }


}
