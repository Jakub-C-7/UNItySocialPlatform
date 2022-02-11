package com.example.demo.searchusers;

import com.example.demo.appuser.AppUser;
import com.example.demo.appuser.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * SearchUsersController Class provides routing to the searachusers page.
 *
 * If the searchusers page is entered without a search criteria input, all users are retrieved. If a search input is
 * detected, the input it split into separate words (separated by spaces), and the AppUserRepository is called to
 * search the database for users with a matching first or last name.
 *
 * @author jakub
 */
@Controller
@AllArgsConstructor
public class SearchUsersController {

    private final AppUserRepository appUserRepository;

    @RequestMapping("/searchusers")
    public String findMatchingUser(String keyword, Model model) {
        if (!keyword.isEmpty()) {

            String [] nameArray = keyword.split(" ");
            if (nameArray.length == 2) {

                List<AppUser> users = appUserRepository.findByFirstNameOrLastName(nameArray[0], nameArray[1]);
                model.addAttribute("users", users);
            } else {
                List<AppUser> users = appUserRepository.findByFirstNameOrLastName(nameArray[0], nameArray[0]);
                model.addAttribute("users", users);
            }

        } else {
            List<AppUser> users = appUserRepository.findAll();
            model.addAttribute("users", users);
        }

        return "searchusers";
    }

}
