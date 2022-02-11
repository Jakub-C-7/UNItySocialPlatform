package com.example.demo.searchusers;

import com.example.demo.appuser.AppUser;
import com.example.demo.appuser.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * SearchUsersController Class provides routing to the searachusers page.
 *
 * Upon entering the search users page (GET request) the SearchUsersController calls the AppUserRepository to retrieve
 * all users and populates the page with results.
 *
 * @author jakub
 */
@Controller
//@RequestMapping("searchusers")
@AllArgsConstructor
public class SearchUsersController {

    private final AppUserRepository appUserRepository;

//    @GetMapping
//    public String getAllUsers(Model model) {
//        List<AppUser> users = appUserRepository.findAll();
//        model.addAttribute("users", users);
//
//        return "searchusers";
//    }

//    @GetMapping("{usersName}")

//    public String findMatchingUser(@PathVariable("usersName") String usersName, Model model) {
    @RequestMapping(path = {"/" , "/searchusers"})
    public String findMatchingUser(String keyword, Model model) {
        if (!keyword.isEmpty()) {

            String [] nameArray = keyword.split(" ");
            if (nameArray.length == 2) {

                List<AppUser> users = appUserRepository.findByFirstNameOrLastName(nameArray[0], nameArray[1]);
                System.out.println(keyword);
                model.addAttribute("users", users);
            } else {
                List<AppUser> users = appUserRepository.findByFirstNameOrLastName(nameArray[0], nameArray[0]);
                model.addAttribute("users", users);
                System.out.println(keyword);
            }
            System.out.println(keyword);


        } else {
            List<AppUser> users = appUserRepository.findAll();
            model.addAttribute("users", users);
        }

        return "searchusers";
    }

}


//        //Removing the first few metadata characters from the search input
//        String sanitisedUsersName = usersName.substring(4, usersName.length());
//
//        //Splitting the sanitised input where there is a + sign
//        String [] nameArray = sanitisedUsersName.split("\\+");
//
//
//        //If a first and last name have been entered
//        if (nameArray.length == 2){
//            List<AppUser> users = appUserRepository.findByFirstNameOrLastName(nameArray[0], nameArray[1]);
//
//            model.addAttribute("users", users);
//
//            return "redirect:/searchusers/{sanitisedUsersName}";
//
//        } else {
//            String [] singleNameArray = usersName.split("=");
//
//            String search = singleNameArray[1];
//
//            List<AppUser> users = appUserRepository.findByFirstNameOrLastName(search, search);
//
//            model.addAttribute("users", users);
//
//            return "redirect:/searchusers/{search}";

//        }

//        return "redirect:/searchusers";

//    }

//}