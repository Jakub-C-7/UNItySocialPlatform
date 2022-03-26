package com.example.demo.personalprofile;

import com.example.demo.appuser.AppUser;
import com.example.demo.appuser.AppUserRepository;
import com.example.demo.post.Post;
import com.example.demo.post.PostService;
import com.example.demo.postComment.PostCommentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

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
    private final PostService postService;
    private final PostCommentService postCommentService;

    @GetMapping
    public String getPersonalProfileView(Principal principal, Model model){

        AppUser loggedInUser = appUserRepository.findByEmail(principal.getName()).get();

        model.addAttribute("user", loggedInUser);

        List<Post> posts = postService.getPostsByUserId(loggedInUser.getId());
        model.addAttribute("posts", posts);
        model.addAttribute("principal", principal);
        model.addAttribute("postService", postService);
        model.addAttribute("postCommentService", postCommentService);

        return "personalprofile";
    }

}
