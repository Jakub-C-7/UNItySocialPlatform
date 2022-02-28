package com.example.demo.chat;

import com.example.demo.appuser.AppUser;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Chat Class represents an instance of a chat between users.
 *
 * @author jakub
 */
@Entity
@Table
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class Chat {

    /**
     * Unique ID for each chat which is incrementally generated in a sequence.
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
    @JoinColumn(name = "participant_one_id")
    private AppUser participantOne;


    @ManyToOne
    @JoinColumn(name = "participant_two_id")
    private AppUser participantTwo;

    public Chat(Long id, AppUser participantOne, AppUser participantTwo) {
        this.id = id;
        this.participantOne = participantOne;
        this.participantTwo = participantTwo;
    }

}
