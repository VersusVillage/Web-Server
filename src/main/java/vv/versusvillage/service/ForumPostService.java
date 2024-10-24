package vv.versusvillage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vv.versusvillage.domain.ForumPost;
import vv.versusvillage.exception.ForumPostNotFoundException;
import vv.versusvillage.repository.ForumPostRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ForumPostService {
    private final ForumPostRepository forumPostRepository;

    @Autowired
    public ForumPostService(ForumPostRepository forumPostRepository) {
        this.forumPostRepository = forumPostRepository;
    }

    @Transactional
    public ForumPost savePost(ForumPost forumPost) {
        if (forumPost.getCreatedAt() == null) {
            forumPost.setCreatedAt(LocalDateTime.now());
        }
        return forumPostRepository.save(forumPost);
    }

    @Transactional
    public Optional<ForumPost> findPostById(Long id) {
        return forumPostRepository.findById(id);
    }

    @Transactional
    public List<ForumPost> findPostsByAuthor(String nickname, String category) {
        return forumPostRepository.findByAuthor(nickname, category);
    }

    @Transactional
    public List<ForumPost> findPostsByTitle(String title, String category) {
        return forumPostRepository.findByTitle(title, category);
    }

    @Transactional
    public List<ForumPost> findAllPosts(String category) {
        return forumPostRepository.findAll(category);
    }

    @Transactional
    public ForumPost updatePost(ForumPost forumPost) {
        return forumPostRepository.update(forumPost);
    }

    @Transactional
    public void deletePost(Long id) {
        ForumPost deletePost = forumPostRepository.findById(id)
                .orElseThrow(() -> new ForumPostNotFoundException("해당 포스트는 존재하지 않습니다."));
        forumPostRepository.delete(deletePost);
    }
}
