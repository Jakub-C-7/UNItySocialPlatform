package com.example.demo.groupPost;

import com.example.demo.appuser.AppUser;
import com.example.demo.group.AppGroup;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * GroupPost Class represents an instance of a post in a group.
 *
 * @author jakub
 */
@Entity
@Table
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class GroupPost {

    /**
     * Unique ID for each post which is incrementally generated in a sequence.
     */
    @Id
    @SequenceGenerator(
            name = "grouppost_sequence",
            sequenceName = "post_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "grouppost_sequence"
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

    @ManyToOne
    @JoinColumn(name = "group_id")
    private AppGroup group;

    /**
     * DateTime of when the post was created.
     */
    private LocalDateTime postDateTime;

    public GroupPost(String postContent, AppUser postUser, AppGroup group, LocalDateTime postDateTime) {
        this.postContent = postContent;
        this.postUser = postUser;
        this.group = group;
        this.postDateTime = postDateTime;
    }
}
