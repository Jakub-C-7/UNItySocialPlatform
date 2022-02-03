package com.example.demo.editpersonalprofile;

import com.example.demo.appuser.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;

/**
 * EditPersonalProfileController Class initiates methods to edit the personal profile upon data input and submission.
 */
@RestController
@RequestMapping("editpersonalprofile")
@AllArgsConstructor
public class EditPersonalProfileController {

    private final EditPersonalProfileService editPersonalProfileService;

    @PostMapping()
    public RedirectView editDetails(EditProfileRequest request, Principal principal){
        RedirectView redirectView = new RedirectView();

        boolean firstNameResult = editPersonalProfileService.editFirstName(request, principal.getName());
        boolean lastNameResult = editPersonalProfileService.editLastName(request, principal.getName());
        boolean emailResult = editPersonalProfileService.editEmail(request, principal.getName());

        // If firstname, lastname, and email changes all succeed
        if(firstNameResult || lastNameResult || emailResult){


            // TODO: Refresh the principal to reflect changes made straight away
            // Could achieve this by following the same pattern that FeedController uses
            // in it's GetMapping annotation to retrieve posts.

            // AKA: Make the user's personal profile pull data from the database.

            redirectView.setUrl("editpersonalprofile?success");

        } else {
            redirectView.setUrl("editpersonalprofile?error");
        }

        return redirectView;
    }
}
