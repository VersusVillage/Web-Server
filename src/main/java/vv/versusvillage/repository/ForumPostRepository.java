package vv.versusvillage.repository;

import vv.versusvillage.domain.ForumPost;

import java.util.List;
import java.util.Optional;

public interface ForumPostRepository {
    ForumPost save(ForumPost forumPost);

    Optional<ForumPost> findById(Long id);

    List<ForumPost> findByTitle(String title, String category);

    List<ForumPost> findByAuthor(String nickname, String category);

    List<ForumPost> findAll(String category);

    ForumPost update(ForumPost forumPost);

    void delete(ForumPost forumPost);
}
