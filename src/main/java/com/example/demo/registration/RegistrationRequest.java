package com.example.demo.registration;

import lombok.*;

/**
 * RegistrationRequest class represents the data request that is formed when a user attempts to register and create
 * a new account using the registration page.
 *
 * @author jakub
 */
@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RegistrationRequest {
    private final String firstName;
    private final String lastName;
    private final String password;
    private final String email;
}
