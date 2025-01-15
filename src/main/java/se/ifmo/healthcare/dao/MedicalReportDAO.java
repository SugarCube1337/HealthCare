package se.ifmo.healthcare.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import se.ifmo.healthcare.models.MedicalReport;

import java.util.List;

@Repository
public class MedicalReportDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void save(MedicalReport medicalReport) {
        entityManager.persist(medicalReport);
    }

    public MedicalReport findById(Long id) {
        return entityManager.find(MedicalReport.class, id);
    }

    public List<MedicalReport> findAll() {
        return entityManager.createQuery("SELECT d FROM MedicalReport d", MedicalReport.class).getResultList();
    }

    @Transactional
    public void delete(Long id) {
        MedicalReport medicalReport = findById(id);
        if (medicalReport != null) {
            entityManager.remove(medicalReport);
        }
    }
}
