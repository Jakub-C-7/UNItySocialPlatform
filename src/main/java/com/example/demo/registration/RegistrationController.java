package com.example.demo.registration;

import com.example.demo.appuser.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.*;

/**
 * Registration Controller Class routes to the registration page.
 *
 * Upon the detection of a POST request, takes user inputs and creates a new RegistrationRequest instance,
 * checks to see if a user with the entered email already exists, displays an error message to the user if they do or
 * proceeds with calling the registrationService to register the user.
 *
 * @author jakub
 */
@RestController
@RequestMapping("register")
@AllArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;
    private final AppUserRepository appUserRepository;

    @PostMapping
    public RedirectView register(RegistrationRequest request){
        RedirectView redirectView = new RedirectView();

        boolean userExists = appUserRepository.findByEmail(request.getEmail()).isPresent();
        if (!userExists){
            registrationService.register(request);
            redirectView.setUrl("register?success");

        } else {
            redirectView.setUrl("register?error");

        }

        return redirectView;
    }

//    @GetMapping(path = "confirm")
//    public String confirm(@RequestParam("token") String token){
//    return registrationService.confirmToken(token);
//    }
}
