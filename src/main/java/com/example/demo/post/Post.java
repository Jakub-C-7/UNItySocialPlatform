package com.example.demo.post;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

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
     * Unique ID for each user that is incrementally generated.
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
