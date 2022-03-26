package com.example.demo.postLike;

import com.example.demo.appuser.AppUser;
import com.example.demo.post.Post;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * PostLike Class represents an instance of a post on the news feed.
 *
 * @author jakub
 */
@Entity
@Table
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class PostLike {

    /**
     * Unique ID for each post which is incrementally generated in a sequence.
     */
    @Id
    @SequenceGenerator(
            name = "post_like_sequence",
            sequenceName = "post_like_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "post_like_sequence"
    )
    private Long id;

    /**
     * Pointer to the Post which has been liked (uses post ID).
     */
    @ManyToOne
    @JoinColumn(name = "liked_post_id")
    private Post post;

    /**
     * Pointer to the AppUser who authored the like (uses user ID).
     */
    @ManyToOne
    @JoinColumn(name = "postlike_user_id")
    private AppUser likeUser;

    public PostLike(Post post, AppUser likeUser) {
        this.post = post;
        this.likeUser = likeUser;
    }
}
