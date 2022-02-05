package com.example.demo.editpersonalprofile;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;

/**
 * EditPersonalProfileController Class provides routing to the editpersonalprofile page.
 *
 * Initiates methods to edit the personal profile on a POST request which takes user input and calls the
 * EditPersonalProfileService class in an attempt to edit a user's database record.
 *
 * @author jakub
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
