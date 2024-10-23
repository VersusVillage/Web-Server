package vv.versusvillage.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import vv.versusvillage.domain.ForumComment;
import vv.versusvillage.domain.ForumPost;

import java.util.List;
import java.util.Optional;

@Repository
public class ForumCommentJpaRepository implements ForumCommentRepository {

    @PersistenceContext
    private final EntityManager em;

    public ForumCommentJpaRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public ForumComment save(ForumComment forumComment) {
        em.persist(forumComment);
        return forumComment;
    }

    @Override
    public Optional<ForumComment> findById(Long id) {
        ForumComment forumComment = em.find(ForumComment.class, id);
        return Optional.ofNullable(forumComment);
    }

    @Override
    public List<ForumComment> findByPost(Long parentId) {
        List<ForumComment> comments = em.createQuery("select f from ForumComment f where f.parentPost.forumPostId = :parentId", ForumComment.class)
                .setParameter("parentId", parentId)
                .getResultList();
        return comments;
    }

    @Override
    public List<ForumComment> findAll() {
        return em.createQuery("select f from ForumComment f", ForumComment.class).getResultList();
    }

    @Override
    public void delete(ForumComment forumComment) {
        em.remove(forumComment);
    }
}
