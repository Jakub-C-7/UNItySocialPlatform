package com.example.demo.feed;

import com.example.demo.post.Post;
import com.example.demo.post.PostService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * FeedController Class provides routing to the feed page.
 *
 * Upon entering the feed page (GET request) the PostService to retrieve all valid user posts and populates
 * the feed page in order from newest to oldest. When a user attempts to create a new post (POST request),
 * the PostService is called to validate user input, create a new post, add it to the database as a new post,
 * and display it on the feed.
 *
 * @author jakub
 */
@Controller
@RequestMapping("feed")
@AllArgsConstructor
public class FeedController {

    private final PostService postService;

    private static List<Post> posts = new ArrayList<>();

    @PostMapping
    public RedirectView newPost(
            Post post,
            Principal principal,
            LocalDateTime localdatetime) {

        RedirectView redirectView = new RedirectView();

        if (post.getPostContent().equals("")){
            redirectView.setUrl("feed?postempty");
            return redirectView;
        }

        postService.createNewPost(post, principal, localdatetime.now());

        redirectView.setUrl("feed?postsuccess");

        return redirectView;
    }

    @GetMapping
    public String getAllPosts(Model model) {
        List<Post> posts = postService.getPosts();
        model.addAttribute("posts", posts);

        return "feed";
    }

}
