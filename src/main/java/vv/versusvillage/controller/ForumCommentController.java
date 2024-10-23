package vv.versusvillage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vv.versusvillage.domain.ForumComment;
import vv.versusvillage.domain.ForumPost;
import vv.versusvillage.exception.ForumPostNotFoundException;
import vv.versusvillage.service.ForumCommentService;
import vv.versusvillage.service.ForumPostService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/comments")
public class ForumCommentController {
    private final ForumCommentService forumCommentService;
    private final ForumPostService forumPostService;

    @Autowired
    public ForumCommentController(ForumCommentService forumCommentService, ForumPostService forumPostService) {
        this.forumCommentService = forumCommentService;
        this.forumPostService = forumPostService;
    }

    @GetMapping
    public ResponseEntity<List<ForumComment>> getCommentsByPost(@RequestParam("postId") Long id) {
        List<ForumComment> comments = forumCommentService.findCommentsByPost(id);
        return ResponseEntity.status(HttpStatus.OK).body(comments);
    }

    @PostMapping
    public ResponseEntity<ForumComment> addComment(@RequestParam("postId") Long postId, ForumComment forumComment) {
        ForumPost parentPost = forumPostService.findPostById(postId)
                .orElseThrow(() -> new ForumPostNotFoundException("Post not found with id: " + postId));

        forumComment.setParentPost(parentPost);
        forumComment.setCreatedAt(LocalDateTime.now());

        ForumComment savedComment = forumCommentService.createComment(forumComment);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedComment);
    }
}
