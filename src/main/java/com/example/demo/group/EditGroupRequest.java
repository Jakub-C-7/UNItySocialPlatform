package com.example.demo.group;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * EditGroupRequest class represents the data request that is formed when a user attempts to edit an AppGroup.
 *
 * @author jakub
 */
@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class EditGroupRequest {
    private final String name;
    private final String description;
    private final String type;
}
