package com.example.demo.appuser;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * AppUserService Class contains methods to create new users.
 *
 * Checks to see if the input data contains an email for a user that already exists and if not, hashes the input
 * password using Bcrypt, and stores the user as a new record in the database.
 *
 * @author jakub
 */
@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {

    private final static String USER_NOT_FOUND_MSG = "user with email %s not found";
    private final static String USER_ALREADY_EXISTS_MSG = "user with email %s already exists";
    private final AppUserRepository appUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return appUserRepository.findByEmail(email).orElseThrow(()->
                new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));
    }

    /**
     * Function for signing up new users. Users get stored in the database if their input email isn't already taken.
     * Passwords are hashed using bcrypt. An exception is thrown if the email is already taken.
     * @param appUser Instance of AppUser class containing details to be signed up.
     */
    public void signUpUser(AppUser appUser){
        boolean userExists = appUserRepository.findByEmail(appUser.getEmail()).isPresent();

        if (userExists){
            throw new AuthenticationServiceException(String.format(USER_ALREADY_EXISTS_MSG, appUser.getEmail()));
        }

        //Hash password
        String encodedPassword = bCryptPasswordEncoder.encode(appUser.getPassword());
        appUser.setPassword(encodedPassword);

        //Set the user's profile avatar
        int rand = (int) (Math.random() * 1000);
        appUser.setProfilePicture("https://avatars.dicebear.com/api/avataaars/" + appUser.getFullName() + rand +".svg");

        //Register a new user
        appUserRepository.save(appUser);
    }

    /**
     * Function for deleting users from the database by their unique user ID.
     * @param id
     */
    public void deleteUser(Long id) {
        boolean exists = appUserRepository.existsById(id);
        if (!exists){
            throw new IllegalStateException("user with id " + id + " does not exist");
        }
        appUserRepository.deleteById(id);
    }
}
