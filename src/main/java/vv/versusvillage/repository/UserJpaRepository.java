package vv.versusvillage.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import vv.versusvillage.domain.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class UserJpaRepository implements UserRepository {
    @PersistenceContext
    private final EntityManager em;

    public UserJpaRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public User save(User user) {
        em.persist(user);
        return user;
    }

    @Override
    public Optional<User> findById(Long id) {
        User member = em.find(User.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<User> findByNickname(String nickname) {
        try {
            User user = em.createQuery("select u from User u where u.userNickname = :nickname", User.class)
                    .setParameter("nickname", nickname)
                    .getSingleResult();
            return Optional.of(user);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<User> findAll() {
        return em.createQuery("select u from User u", User.class).getResultList();
    }
}
