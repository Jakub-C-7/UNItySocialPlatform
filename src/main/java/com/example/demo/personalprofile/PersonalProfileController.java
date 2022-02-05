package com.example.demo.personalprofile;

import com.example.demo.appuser.AppUser;
import com.example.demo.appuser.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

/**
 * PersonalProfileController Class provides routing to the personalprofile page.
 *
 * The personalprofile page can only be seen by the logged-in user and displays their personal
 * profile view.
 *
 * @author jakub
 */
@Controller
@RequestMapping("personalprofile")
@AllArgsConstructor
public class PersonalProfileController {

    private final AppUserRepository appUserRepository;

    @GetMapping
    public String getPersonalProfileView(Principal principal, Model model){

        AppUser loggedInUser = appUserRepository.findByEmail(principal.getName()).get();

        model.addAttribute("user", loggedInUser);

        return "personalprofile";
    }

}
