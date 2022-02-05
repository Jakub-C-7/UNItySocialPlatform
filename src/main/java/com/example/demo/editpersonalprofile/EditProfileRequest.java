package com.example.demo.editpersonalprofile;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * EditProfileRequest class represents the data request that is formed when a user attempts to edit their
 * personal profile.
 *
 * @author jakub
 */
@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class EditProfileRequest {
    private final String firstName;
    private final String lastName;
    private final String email;
}
