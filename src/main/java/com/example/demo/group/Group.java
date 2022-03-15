package com.example.demo.group;

import com.example.demo.appuser.AppUser;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Group Class represents an instance of a user created group.
 */
@Entity
@Table
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class Group {

    /**
     * Unique ID for each group that is incrementally generated.
     */
    @Id
    @SequenceGenerator(
            name = "group_sequence",
            sequenceName = "group_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "group_sequence"
    )
    private Long id;

    /**
     * The string containing the name of the group.
     */
    private String name;

    /**
     * The string containing the description of the group.
     */
    private String description;

    /**
     * The string containing the type of the group.
     */
    private String type;

    /**
     * ID of the user who created the group.
     */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private AppUser creator;

    public Group(String name, String description, String type) {
        this.name = name;
        this.description = description;
        this.type = type;
    }
}
