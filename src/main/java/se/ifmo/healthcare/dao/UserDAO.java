package se.ifmo.healthcare.dao;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import jdk.jfr.Registered;
import org.springframework.stereotype.Repository;
import se.ifmo.healthcare.models.User;

import java.util.List;
import java.util.Optional;

@Repository
public class UserDAO {
    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void save(User user) {
        try {
            em.persist(user);
        } catch (Exception e) {
            throw e;
        }
    }


    public User findById(Integer id) {
        return em.find(User.class, id);

    }



    public List<User> findAll() {
        return em.createQuery("SELECT u FROM User u", User.class).getResultList();

    }

    @Transactional
    public void update(User user) {
        try {
            em.merge(user);

        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional
    public void delete(Integer id) {
        try {
            User user = em.find(User.class, id);
            if (user != null) {
                em.remove(user);
            } else {
                throw new EntityNotFoundException("User not found");
            }
        } catch (Exception e) {
            throw e;
        }
    }


    public Optional<User> findByUsername(String username) {
        TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class);
        query.setParameter("username", username);
        return query.getResultStream().findFirst();
    }

    public User findUserByName(String name) {
        try {
            return em.createQuery("SELECT u FROM User u WHERE u.username = :name", User.class)
                    .setParameter("name", name)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
