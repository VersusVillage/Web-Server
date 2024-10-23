package vv.versusvillage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vv.versusvillage.domain.ForumComment;
import vv.versusvillage.repository.ForumCommentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ForumCommentService {
    private final ForumCommentRepository forumCommentRepository;

    @Autowired
    public ForumCommentService(ForumCommentRepository forumCommentRepository) {
        this.forumCommentRepository = forumCommentRepository;
    }

    @Transactional
    public ForumComment createComment(ForumComment forumComment) {
        return forumCommentRepository.save(forumComment);
    }

    @Transactional
    public Optional<ForumComment> findCommentById(Long id) {
        return forumCommentRepository.findById(id);
    }

    @Transactional
    public List<ForumComment> findCommentsByPost(Long parentId) {
        return forumCommentRepository.findByPost(parentId);
    }
}
