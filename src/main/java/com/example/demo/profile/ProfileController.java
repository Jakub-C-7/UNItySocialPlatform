package com.example.demo.profile;

import com.example.demo.appuser.AppUser;
import com.example.demo.appuser.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.EntityNotFoundException;
import java.security.Principal;

/**
 * ProfileController class provides routing to user profiles via user ID.
 *
 * Requests mapping to the 'profile' route which by default redirects the logged-in user to
 * their personal profile. Allows an input of a user's ID (e.g. profile/25) via the URL to view and
 * browse that user's profile. Checks to see if a user with the input ID exists and if they exist,
 * allows access to their profile, and if they don't exist, redirects back to the logged-in user's
 * personal profile. If the logged-in user is attempting to enter their own profile in this way, the
 * controller redirects them to the 'personalprofile' view of their profile which allows for personalisation.
 *
 * @author jakub
 */
@Controller
@RequestMapping("profile")
@AllArgsConstructor
public class ProfileController {

    private final AppUserRepository appUserRepository;

    @GetMapping
    public String defaultRoute (){
        return "redirect:/personalprofile";
    }

    @GetMapping("{userId}")
    public String getUserProfile(@PathVariable("userId") Long userId, Model model, Principal principal){
        boolean userExists = appUserRepository.findById(userId).isPresent();

        if (!userExists){
            return "redirect:/personalprofile";
        }

        AppUser user = appUserRepository.getById(userId);

        AppUser loggedInUser = appUserRepository.findByEmail(principal.getName()).get();

        if (loggedInUser.getId().equals(user.getId())){
            return "redirect:/personalprofile";
        }

        model.addAttribute("user", user);

        return "profile";
    }
}
