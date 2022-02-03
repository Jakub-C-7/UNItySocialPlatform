package com.example.demo.registration;

import com.example.demo.appuser.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.*;

@RestController
@RequestMapping("register")
@AllArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;
    private final AppUserRepository appUserRepository;

    @PostMapping
    public RedirectView register(RegistrationRequest request){ //REMOVED THE REQUEST MAPPING ANNOTATION
        RedirectView redirectView = new RedirectView();

        boolean userExists = appUserRepository.findByEmail(request.getEmail()).isPresent();
        if(!userExists){
            registrationService.register(request);
            redirectView.setUrl("register?success"); // Redirects to success view

            return redirectView;
        } else {
            redirectView.setUrl("register?error"); // Redirects to error view

            return redirectView;
        }
    }

//    @GetMapping(path = "confirm")
//    public String confirm(@RequestParam("token") String token){
//    return registrationService.confirmToken(token);
//    }
}
