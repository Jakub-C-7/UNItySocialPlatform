package com.example.demo.searchusers;

import com.example.demo.appuser.AppUser;
import com.example.demo.appuser.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * SearchUsersController Class provides routing to the searachusers page.
 *
 * Upon entering the search users page (GET request) the SearchUsersController calls the AppUserRepository to retrieve
 * all users and populates the page with results.
 *
 * @author jakub
 */
@Controller
@RequestMapping("searchusers")
@AllArgsConstructor
public class SearchUsersController {

    private final AppUserRepository appUserRepository;


    @GetMapping
    public String getAllUsers(Model model) {
        List<AppUser> users = appUserRepository.findAll();
        model.addAttribute("users", users);

        return "searchusers";
    }

}