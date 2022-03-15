package com.example.demo.groupMember;

import com.example.demo.appuser.AppUser;
import com.example.demo.group.Group;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * GroupMember class represents an instance of a user's membership to a group.
 *
 * @author jakub
 */
@Entity
@Table
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class GroupMember {

    /**
     * Unique ID for each group member which is incrementally generated in a sequence.
     */
    @Id
    @SequenceGenerator(
            name = "groupMember_sequence",
            sequenceName = "friend_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "groupMember_sequence"
    )
    private Long id;

    /**
     * ID of the group that the user belongs to.
     */
    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    /**
     * ID of the user who's the member of the group.
     */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private AppUser user;

    /**
     * True if the user has been accepted and added as a member, false if the user is still waiting for acceptance.
     */
    private Boolean added;

    /**
     * The role of the user within the group
     */
    @Enumerated(EnumType.STRING)
    private GroupMemberRole role;
}
