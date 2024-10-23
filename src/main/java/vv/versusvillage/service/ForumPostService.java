package vv.versusvillage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vv.versusvillage.domain.ForumPost;
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
    public List<ForumPost> findPostsByAuthor(String nickname) {
        return forumPostRepository.findByAuthor(nickname);
    }

    @Transactional
    public List<ForumPost> findPostsByTitle(String title) {
        return forumPostRepository.findByTitle(title);
    }

    @Transactional
    public List<ForumPost> findAllPosts() {
        return forumPostRepository.findAll();
    }

    @Transactional
    public void deletePost(Long id) {
        forumPostRepository.delete(id);
    }
}
