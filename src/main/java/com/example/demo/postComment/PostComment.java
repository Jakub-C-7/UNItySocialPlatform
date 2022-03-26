package com.example.demo.postComment;

import com.example.demo.appuser.AppUser;
import com.example.demo.post.Post;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * PostComment Class represents an instance of a comment under a post.
 *
 * @author jakub
 */
@Entity
@Table
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class PostComment {

    /**
     * Unique ID for each post which is incrementally generated in a sequence.
     */
    @Id
    @SequenceGenerator(
            name = "post_comment_sequence",
            sequenceName = "post_comment_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "post_comment_sequence"
    )
    private Long id;

    /**
     * Pointer to the Post which has been commented on (uses post ID).
     */
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    /**
     * Pointer to the AppUser who authored the comment (uses user ID).
     */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private AppUser commentAuthor;

    private String commentContent;


    public PostComment(Post post, AppUser likeUser) {
        this.post = post;
        this.commentAuthor = likeUser;
    }
}
