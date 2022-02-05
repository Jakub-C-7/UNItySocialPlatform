package com.example.demo.registration;

import com.example.demo.appuser.AppUser;
import com.example.demo.appuser.AppUserRole;
import com.example.demo.appuser.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * RegistrationService Class calls upon the AppUserService class to register users.
 *
 * Deconstructs the user's input parameters and calls the signUpUser function to create a new user in the database.
 *
 * @author jakub
 */
@Service
@AllArgsConstructor
public class RegistrationService {

    private final AppUserService appUserService;

    /**
     * Register method calls the AppUserService to register a user.
     * @param request The registration request parsed in through the request.
     */
    public void register(RegistrationRequest request) {
//        boolean includeErrors;

         appUserService.signUpUser(
                new AppUser(
                        request.getFirstName(),
                        request.getLastName(),
                        request.getEmail(),
                        request.getPassword(),
                        AppUserRole.USER
                )
        );
    }

}
