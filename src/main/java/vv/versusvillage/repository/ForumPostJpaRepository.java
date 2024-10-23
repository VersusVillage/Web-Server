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
        if (forumPost.getForumPostId() != null) {
            return em.merge(forumPost);
        } else {
            em.persist(forumPost);
            return forumPost;
        }
    }

    @Override
    public Optional<ForumPost> findById(Long id) {
        ForumPost forumPost = em.find(ForumPost.class, id);
        return Optional.ofNullable(forumPost);
    }

    @Override
    public List<ForumPost> findByTitle(String title) {
        List<ForumPost> forumPosts = em.createQuery("select f from ForumPost f where f.title LIKE :title", ForumPost.class)
                .setParameter("title", "%" + title + "%")
                .getResultList();
        return forumPosts;
    }

    @Override
    public List<ForumPost> findByAuthor(String nickname) {
        List<ForumPost> forumPosts = em.createQuery("select f from ForumPost f where f.author = :author", ForumPost.class)
                .setParameter("author", nickname)
                .getResultList();
        return forumPosts;
    }

    @Override
    public List<ForumPost> findAll() {
        return em.createQuery("select f from ForumPost f", ForumPost.class).getResultList();
    }

    @Override
    public void delete(Long id) {
        em.createQuery("delete from ForumPost f where f.forumPostId = :id", ForumPost.class)
                .setParameter("id", id)
                .executeUpdate();
    }
}
