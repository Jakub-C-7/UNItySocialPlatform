package com.example.demo.editpersonalprofile;

import com.example.demo.appuser.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * EditPersonalProfileService Class provides functions to edit a user's profile details.
 *
 * Contains business logic for taking user input and allow users to edit their email, first name,
 * and last name in the database.
 *
 * @author jakub
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

        if (request.getEmail() != ""){
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

        if (request.getFirstName() != ""){
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

        if (request.getLastName() != ""){
            appUserRepository.updateLastName(email, request.getLastName());
            return true;
        }
        return false;
    }

    /**
     * Function for editing a user's personal profile bio to a string entered and parsed through the request.
     * @param request The request body.
     * @param email Current email string of the user attempting to edit their profile bio.
     * @return True on success or False on failure.
     */
    public boolean editProfileBio(EditProfileRequest request, String email){

        if (request.getProfileBio() != ""){
            appUserRepository.updateProfileBio(email, request.getProfileBio());
            return true;
        }
        return false;
    }

    /**
     * Function for editing a user's personal academic course to a string entered and parsed through the request.
     * @param request The request body.
     * @param email Current email string of the user attempting to edit their profile bio.
     * @return True on success or False on failure.
     */
    public boolean editAcademicCourse(EditProfileRequest request, String email){

        if (request.getAcademicCourse() != ""){
            appUserRepository.updateAcademicCourse(email, request.getAcademicCourse());
            return true;
        }
        return false;
    }

}
