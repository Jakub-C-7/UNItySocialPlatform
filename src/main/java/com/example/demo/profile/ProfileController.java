package com.example.demo.profile;

import com.example.demo.appuser.AppUser;
import com.example.demo.appuser.AppUserRepository;
import com.example.demo.friend.Friend;
import com.example.demo.friend.FriendRepository;
import com.example.demo.post.Post;
import com.example.demo.post.PostService;
import com.example.demo.postComment.PostCommentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.EntityNotFoundException;
import java.security.Principal;
import java.util.List;

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
    private final PostService postService;
    private final FriendRepository friendRepository;
    private final PostCommentService postCommentService;

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

        if (principal != null) {
            AppUser loggedInUser = appUserRepository.findByEmail(principal.getName()).get();

            if (loggedInUser.getId().equals(user.getId())){
                return "redirect:/personalprofile";
            }

            Boolean areFriends;
            Friend friendRecord;
            //Check to see if the current user is this user's friend
            if (!friendRepository.findByUserAndUsersFriend(loggedInUser, user).isEmpty()){
                areFriends = true;
                friendRecord = friendRepository.findByUserAndUsersFriend(loggedInUser, user).get(0);
                model.addAttribute("friendRecord", friendRecord);

            } else if (!friendRepository.findByUserAndUsersFriend(user, loggedInUser).isEmpty()) {
                 areFriends = true;
                 friendRecord = friendRepository.findByUserAndUsersFriend(user, loggedInUser).get(0);
                 model.addAttribute("friendRecord", friendRecord);
            }

            else {
                 areFriends = false;
            }
            model.addAttribute("areFriends", areFriends);
            model.addAttribute("postService", postService);
            model.addAttribute("postCommentService", postCommentService);
            model.addAttribute("principal", principal);

        }

        model.addAttribute("user", user);

        List<Post> posts = postService.getPostsByUserId(userId);
        model.addAttribute("posts", posts);

        return "profile";
    }
}
