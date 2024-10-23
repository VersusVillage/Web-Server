package vv.versusvillage.repository;

import vv.versusvillage.domain.ForumPost;

import java.util.List;
import java.util.Optional;

public interface ForumPostRepository {
    ForumPost save(ForumPost forumPost);
    Optional<ForumPost> findById(Long id);
    List<ForumPost> findByTitle(String title);
    List<ForumPost> findByAuthor(String nickname);
    List<ForumPost> findAll();
    void delete(Long id);
}
