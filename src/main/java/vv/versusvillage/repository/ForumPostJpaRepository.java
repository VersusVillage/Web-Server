package vv.versusvillage.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import vv.versusvillage.domain.ForumPost;

import javax.swing.text.html.parser.Entity;
import java.util.List;
import java.util.Optional;

@Repository
public class ForumPostJpaRepository implements ForumPostRepository {
    @PersistenceContext
    private final EntityManager em;

    public ForumPostJpaRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public ForumPost save(ForumPost forumPost) {
        em.persist(forumPost);
        return forumPost;
    }

    @Override
    public ForumPost update(ForumPost forumPost) {
        return em.merge(forumPost);
    }

    @Override
    public Optional<ForumPost> findById(Long id) {
        ForumPost forumPost = em.find(ForumPost.class, id);
        return Optional.ofNullable(forumPost);
    }

    @Override
    public List<ForumPost> findByTitle(String title, String category) {
        return em.createQuery("select f from ForumPost f where f.title LIKE :title and f.category = :category", ForumPost.class)
                .setParameter("title", "%" + title + "%")
                .setParameter("category", category)
                .getResultList();
    }

    @Override
    public List<ForumPost> findByAuthor(String nickname, String category) {
        return em.createQuery("select f from ForumPost f where f.author = :author and f.category = :category", ForumPost.class)
                .setParameter("author", nickname)
                .setParameter("category", category)
                .getResultList();
    }

    @Override
    public List<ForumPost> findAll(String category) {
        return em.createQuery("select f from ForumPost f where f.category = :category", ForumPost.class)
                .setParameter("category", category)
                .getResultList();
    }

    @Override
    public void delete(ForumPost forumPost) {
        em.remove(forumPost);
    }
}
