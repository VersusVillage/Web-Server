package vv.versusvillage.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "forum_comments")
public class ForumComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long forumCommentId;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column
    private String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "forum_post", nullable = false)
    private ForumPost parentPost;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Long getForumCommentId() {
        return forumCommentId;
    }

    public void setForumCommentId(Long forumCommentId) {
        this.forumCommentId = forumCommentId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public ForumPost getParentPost() {
        return parentPost;
    }

    public void setParentPost(ForumPost parentPost) {
        this.parentPost = parentPost;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
