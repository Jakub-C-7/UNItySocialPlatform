package com.example.demo.chatMessage;


import com.example.demo.appuser.AppUser;
import com.example.demo.chat.Chat;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * ChatMessage Class represents an instance of message within a chat.
 *
 * @author jakub
 */
@Entity
@Table
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class ChatMessage {

    /**
     * Unique ID for each message which is incrementally generated in a sequence.
     */
    @Id
    @SequenceGenerator(
            name = "chat_sequence",
            sequenceName = "chat_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "chat_sequence"
    )
    private Long id;

    @ManyToOne
    @JoinColumn(name = "chat_id")
    private Chat chat;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private AppUser author;

    /**
     * The contents of the message
     */
    private String messageContent;

    /**
     * DateTime of when the message was sent.
     */
    private LocalDateTime messageDateTime;

    public ChatMessage(Long id, Chat chat, AppUser author, String messageContent, LocalDateTime messageDateTime) {
        this.id = id;
        this.chat = chat;
        this.author = author;
        this.messageContent = messageContent;
        this.messageDateTime = messageDateTime;
    }
}
