package com.example.demo.post;

import com.example.demo.appuser.AppUser;
import com.example.demo.postLike.PostLikeRepository;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Post Class represents an instance of a post on the news feed.
 *
 * @author jakub
 */
@Entity
@Table
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class Post {

    /**
     * Unique ID for each post which is incrementally generated in a sequence.
     */
    @Id
    @SequenceGenerator(
            name = "post_sequence",
            sequenceName = "post_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "post_sequence"
    )
    private Long id;

    /**
     * String containing the text content of the post.
     */
    private String postContent;

    /**
     * Pointer to the AppUser who authored the post (uses user ID).
     */
    @ManyToOne
    @JoinColumn(name = "post_user_id")
    private AppUser postUser;

    /**
     * DateTime of when the post was created.
     */
    private LocalDateTime postDateTime;

    public Post(String postContent, AppUser postUser, LocalDateTime postDateTime) {
        this.postContent = postContent;
        this.postUser = postUser;
        this.postDateTime = postDateTime;
    }

}
