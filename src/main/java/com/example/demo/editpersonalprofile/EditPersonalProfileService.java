package com.example.demo.editpersonalprofile;

import com.example.demo.appuser.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * EditPersonalProfileService Class performs functions to edit user profile details.
 *
 * Class contains business logic to take user input and edit an email, first name, and last name in the database.
 */
@Service
@AllArgsConstructor
public class EditPersonalProfileService {

    AppUserRepository appUserRepository;

    /**
     * Function for editing a user's personal email to an email entered and parsed through the request. Checks to see
     * if the entered email is already in use.
     * @param request The request body.
     * @param email Current email string of the user attempting to edit their email.
     * @return True on success or False on failure.
     */
    public boolean editEmail(EditProfileRequest request, String email){
        boolean emailAlreadyExists = appUserRepository.findByEmail(request.getEmail()).isPresent();

        if (request.getEmail() != null){
            if (!emailAlreadyExists) {
                appUserRepository.updateEmail(email, request.getEmail());
                return true;
            }
        }
        return false;
    }

    /**
     * Function for editing a user's personal first name to a first name entered and parsed through the request.
     * @param request The request body.
     * @param email Current email string of the user attempting to edit their first name.
     * @return True on success or False on failure.
     */
    public boolean editFirstName(EditProfileRequest request, String email){

        if (request.getFirstName() != null){
            appUserRepository.updateFirstName(email, request.getFirstName());
            return true;
        }

        return false;
    }

    /**
     * Function for editing a user's personal last name to a last name entered and parsed through the request.
     * @param request The request body.
     * @param email Current email string of the user attempting to edit their last name.
     * @return True on success or False on failure.
     */
    public boolean editLastName(EditProfileRequest request, String email){

        if (request.getLastName() != null){
            appUserRepository.updateLastName(email, request.getLastName());
            return true;
        }
        return false;
    }

}
