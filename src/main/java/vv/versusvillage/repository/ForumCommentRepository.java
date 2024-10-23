package vv.versusvillage.repository;

import vv.versusvillage.domain.ForumComment;

import java.util.List;
import java.util.Optional;

public interface ForumCommentRepository {
    ForumComment save(ForumComment forumComment);

    Optional<ForumComment> findById(Long id);

    List<ForumComment> findByPost(Long parentId);

    List<ForumComment> findAll();

    void delete(ForumComment forumComment);
}
