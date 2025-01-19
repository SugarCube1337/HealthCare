package se.ifmo.healthcare.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import se.ifmo.healthcare.models.Patient;
import se.ifmo.healthcare.models.StaffMember;

import java.util.List;

@Repository
public class StaffMemberDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void save(StaffMember staffMember) {
        entityManager.persist(staffMember);
    }

    @Transactional
    public StaffMember update(StaffMember staffMember) {
        return entityManager.merge(staffMember);
    }

    public StaffMember findById(Long id) {
        return entityManager.find(StaffMember.class, id);
    }

    public List<StaffMember> findAll() {
        return entityManager.createQuery("SELECT s FROM StaffMember s", StaffMember.class).getResultList();
    }

    @Transactional
    public void delete(Long id) {
        StaffMember staffMember = findById(id);
        if (staffMember != null) {
            entityManager.remove(staffMember);
        }
    }

    public StaffMember findStaffMemberByPersonId(Long id) {
        try {
            return entityManager.createQuery("SELECT u FROM StaffMember u WHERE u.person.id = :id", StaffMember.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
