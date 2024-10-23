package vv.versusvillage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import vv.versusvillage.domain.ForumPost;
import vv.versusvillage.exception.ForumPostNotFoundException;
import vv.versusvillage.exception.UserNotFoundException;
import vv.versusvillage.service.ForumPostService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/posts")
public class ForumPostController {
    private final ForumPostService forumPostService;

    @Autowired
    public ForumPostController(ForumPostService forumPostService) {
        this.forumPostService = forumPostService;
    }

    @GetMapping
    public ResponseEntity<List<ForumPost>> getAllPosts() {
        List<ForumPost> posts = forumPostService.findAllPosts();
        return ResponseEntity.status(HttpStatus.OK).body(posts);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<ForumPost> getPostById(@PathVariable Long id) {
        Optional<ForumPost> post = forumPostService.findPostById(id);
        return post.map(forumPost -> ResponseEntity.status(HttpStatus.OK).body(forumPost))
                .orElseThrow(() -> new ForumPostNotFoundException("게시글를 찾지 못했습니다. id:  " + id));
    }

    @GetMapping("/author/{nickname}")
    public ResponseEntity<List<ForumPost>> getPostsByAuthor(@PathVariable String nickname) {
        List<ForumPost> forumPosts = forumPostService.findPostsByAuthor(nickname);
        return ResponseEntity.status(HttpStatus.OK).body(forumPosts);
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<List<ForumPost>> getPostsByTitle(@PathVariable String title) {
        List<ForumPost> forumPosts = forumPostService.findPostsByAuthor(title);
        return ResponseEntity.status(HttpStatus.OK).body(forumPosts);
    }

    @PostMapping
    public ResponseEntity<ForumPost> createPost(@RequestBody ForumPost forumPost) {
        ForumPost newPost = forumPostService.savePost(forumPost);
        return ResponseEntity.status(HttpStatus.CREATED).body(newPost);
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<ForumPost> updatePost(@PathVariable Long id, @RequestBody ForumPost updatedPost) {
        updatedPost.setForumPostId(id);
        ForumPost savedPost = forumPostService.updatePost(updatedPost);
        return ResponseEntity.ok(savedPost);
    }
}
