package com.example.demo.profile;

import com.example.demo.appuser.AppUser;
import com.example.demo.appuser.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("profile")
@AllArgsConstructor
public class ProfileController {

    private final AppUserRepository appUserRepository;

    @GetMapping("{userId}")
    public String getUserProfile(@PathVariable("userId") Long userId, Model model, Principal principal){
        AppUser user = appUserRepository.getById(userId);
//        AppUser loggedInUser = appUserRepository.findByEmail(principal.getName()).get();

//        if (loggedInUser.getId().equals(user.getId())){
//            //TODO: IF THE USER IS ACCESSING THEIR OWN PROFILE. SHOW PERSONAL PROFILE MODE
//            return "/personalprofile";
//        }

        model.addAttribute("user", user);

        return "profile";
    }
}
