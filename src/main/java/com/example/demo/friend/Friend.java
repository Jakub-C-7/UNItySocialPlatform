package com.example.demo.friend;

import com.example.demo.appuser.AppUser;
import com.example.demo.chat.Chat;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * Friend represents an instance of a user's friend.
 * @author jakub
 */
@Entity
@Table
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class Friend {

    /**
     * Unique ID for each friend list which is incrementally generated in a sequence.
     */
    @Id
    @SequenceGenerator(
            name = "friend_sequence",
            sequenceName = "friend_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "friend_sequence"
    )
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private AppUser user;

    @ManyToOne
    @JoinColumn(name = "friend_id")
    private AppUser usersFriend;

//    @OneToMany(mappedBy = "participantOne", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Chat> chats1;
//
//    @OneToMany(mappedBy = "participantTwo", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Chat> chats2;

    @OneToMany(mappedBy = "friendshipRecord", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Chat> chats;

    private Boolean added;

}
