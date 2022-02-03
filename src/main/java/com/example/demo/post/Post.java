package com.example.demo.post;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

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

    private String postContent;

    private String postAuthorEmail;

    private String postAuthorName;

    private LocalDateTime postDateTime;

    public Post(String postContent, String postAuthorEmail, String postAuthorName, LocalDateTime postDateTime) {
        this.postContent = postContent;
        this.postAuthorEmail = postAuthorEmail;
        this.postAuthorName = postAuthorName;
        this.postDateTime = postDateTime;
    }
}
